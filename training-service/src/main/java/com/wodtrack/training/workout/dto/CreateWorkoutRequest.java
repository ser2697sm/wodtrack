package com.wodtrack.training.workout.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CreateWorkoutRequest(
        @NotNull @PastOrPresent LocalDate date,
        @Size(max = 500) String notes,
        @NotEmpty @Valid List<WorkoutEntryRequest> entries
        ) {
}
