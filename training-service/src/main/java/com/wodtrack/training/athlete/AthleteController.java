package com.wodtrack.training.athlete;

import com.wodtrack.training.athlete.dto.CreateAthleteRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Athlete")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping
    public ResponseEntity<?> createAthlete(@RequestBody @Valid CreateAthleteRequest createAthleteRequest) {
        return ResponseEntity.ok(athleteService.saveAthete(createAthleteRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewAthlete(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.viewAthete(id));
    }
}
