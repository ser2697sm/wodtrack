package com.wodtrack.training.workout.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WorkoutEntryRequest(
        @NotNull(message = "Debe existir un ejercicio") Long exerciseId,
        @NotNull @Positive
        @Digits(integer = 6, fraction = 2, message = "Máximo 6 cifras enteras y 2 decimales")
        BigDecimal value
) {
}
