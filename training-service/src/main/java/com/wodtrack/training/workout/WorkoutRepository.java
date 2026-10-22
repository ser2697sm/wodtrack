package com.wodtrack.training.workout;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Trae los entrenos con sus líneas y ejercicios en una sola consulta (evita el problema N+1)
    @EntityGraph(attributePaths = {"entries", "entries.exercise"})
    List<Workout> findByAthleteIdAndDateBetweenOrderByDateDesc(Long athleteId, LocalDate from, LocalDate to);
}
