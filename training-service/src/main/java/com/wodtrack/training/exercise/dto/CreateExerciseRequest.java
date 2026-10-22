package com.wodtrack.training.exercise.dto;

import com.wodtrack.training.exercise.TypeExercise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String name,
        @NotNull(message = "El tipo es obligatorio")
        TypeExercise type
) {
}
