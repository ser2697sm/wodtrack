package com.wodtrack.training.workout.dto;

import com.wodtrack.training.exercise.TypeExercise;
import com.wodtrack.training.workout.WorkoutEntry;

import java.math.BigDecimal;

public record WorkoutEntryResponse(
        Long exerciseId,
        String exerciseName,
        TypeExercise type,
        BigDecimal value
) {
    public static WorkoutEntryResponse from(WorkoutEntry entry) {
        return new WorkoutEntryResponse(
                entry.getExercise().getId(),
                entry.getExercise().getName(),
                entry.getExercise().getType(),
                entry.getValue()
        );
    }
}
