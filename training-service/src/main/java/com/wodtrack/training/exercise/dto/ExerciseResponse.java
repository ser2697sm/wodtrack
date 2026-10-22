package com.wodtrack.training.exercise.dto;

import com.wodtrack.training.exercise.Exercise;
import com.wodtrack.training.exercise.TypeExercise;

public record ExerciseResponse(
        Long id,
        String name,
        TypeExercise type
) {
    public static ExerciseResponse from(Exercise exercise) {
        return new ExerciseResponse(exercise.getId(), exercise.getName(), exercise.getType());
    }
}
