package com.wodtrack.training.exercise;

import com.wodtrack.training.common.exception.ConflictException;
import com.wodtrack.training.exercise.dto.CreateExerciseRequest;
import com.wodtrack.training.exercise.dto.ExerciseResponse;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseResponse create(CreateExerciseRequest create) {

        String name = create.name().trim();

        if (exerciseRepository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("El nombre ya existe");
        }

        Exercise exercise = Exercise.builder()
                .name(name)
                .type(create.type())
                .build();

        return ExerciseResponse.from(exerciseRepository.save(exercise));
    }

    @Transactional(readOnly = true)
    public List<ExerciseResponse> findAll() {
      return exerciseRepository.findAll()
              .stream()
              .map(ExerciseResponse::from)
              .toList();


    }
}
