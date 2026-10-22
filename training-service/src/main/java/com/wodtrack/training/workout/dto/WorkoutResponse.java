package com.wodtrack.training.workout.dto;

import com.wodtrack.training.workout.Workout;

import java.time.LocalDate;
import java.util.List;

public record WorkoutResponse(
        Long id,
        LocalDate date,
        String notes,
        List<WorkoutEntryResponse> entries
) {
    public static WorkoutResponse from(Workout workout) {
        return new WorkoutResponse(
                workout.getId(),
                workout.getDate(),
                workout.getNotes(),
                workout.getEntries().stream()
                        .map(WorkoutEntryResponse::from)
                        .toList()
        );
    }
}
