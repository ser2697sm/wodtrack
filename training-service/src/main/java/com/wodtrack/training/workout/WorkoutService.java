package com.wodtrack.training.workout;

import com.wodtrack.training.athlete.Athlete;
import com.wodtrack.training.athlete.AthleteRepository;
import com.wodtrack.training.common.exception.BadRequestException;
import com.wodtrack.training.common.exception.ResourceNotFoundException;
import com.wodtrack.training.exercise.Exercise;
import com.wodtrack.training.exercise.ExerciseRepository;
import com.wodtrack.training.workout.dto.CreateWorkoutRequest;
import com.wodtrack.training.workout.dto.WorkoutEntryRequest;
import com.wodtrack.training.workout.dto.WorkoutResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final AthleteRepository athleteRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;


    public WorkoutService(AthleteRepository athleteRepository, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.athleteRepository = athleteRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public WorkoutResponse create(Long athleteId, CreateWorkoutRequest createWorkoutRequest) {
        // 1. El atleta
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow(() ->
                new ResourceNotFoundException("Atleta no encontrado con id " + athleteId)
        );

        // 2. Los ids de ejercicio que vienen en las líneas
        Set<Long> ids = createWorkoutRequest.entries().stream()
                .map(WorkoutEntryRequest::exerciseId)
                .collect(Collectors.toSet());

        // 3. Una sola consulta para traerlos todos, y los guardo por id
        Map<Long,Exercise> exercises = exerciseRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(Exercise::getId,e -> e));

        // 4. Construyo el entreno y le añado cada línea
        Workout workout = Workout.builder()
                .athlete(athlete)
                .date(createWorkoutRequest.date())
                .notes(createWorkoutRequest.notes())
                .build();


        for (WorkoutEntryRequest entry : createWorkoutRequest.entries()) {
            Exercise exercise = exercises.get(entry.exerciseId());
            if (exercise == null) {
                throw new ResourceNotFoundException("Ejercicio no encontrado con id " + entry.exerciseId());
            }
            workout.addEntry(WorkoutEntry.builder()
                    .exercise(exercise)
                    .value(entry.value())
                    .build());
        }

        // 5. Guardo (las líneas se guardan solas por el cascade) y devuelvo el DTO
        return WorkoutResponse.from(workoutRepository.save(workout));
    }

    @Transactional(readOnly = true)
    public List<WorkoutResponse> findByDateRange(Long athleteId, LocalDate from, LocalDate to) {

        if (from.isAfter(to)) {
            throw new BadRequestException("La fecha de inicio no puede ser posterior a la fecha final");
        }

        if (!athleteRepository.existsById(athleteId)) {
            throw new ResourceNotFoundException("Atleta no encontrado con id " + athleteId);
        }

        return workoutRepository.findByAthleteIdAndDateBetweenOrderByDateDesc(athleteId, from, to)
                .stream()
                .map(WorkoutResponse::from)
                .toList();
    }
}
