package com.wodtrack.training.exercise;

import com.wodtrack.training.exercise.dto.CreateExerciseRequest;
import com.wodtrack.training.exercise.dto.ExerciseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> crete(@RequestBody @Valid CreateExerciseRequest createExerciseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.create(createExerciseRequest));
    }

    @GetMapping
    public ResponseEntity<?> view() {
        return ResponseEntity.ok(exerciseService.findAll());
    }
}
