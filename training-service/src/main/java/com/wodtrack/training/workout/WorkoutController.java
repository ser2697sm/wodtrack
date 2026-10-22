package com.wodtrack.training.workout;

import com.wodtrack.training.workout.dto.CreateWorkoutRequest;
import com.wodtrack.training.workout.dto.WorkoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/athletes/{athleteId}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponse> create(@PathVariable Long athleteId, @RequestBody @Valid CreateWorkoutRequest createWorkoutRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workoutService.create(athleteId, createWorkoutRequest));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> view(@PathVariable Long athleteId, @RequestParam LocalDate from,@RequestParam LocalDate to) {
        return ResponseEntity.ok(workoutService.findByDateRange(athleteId, from,to));
    }
}
