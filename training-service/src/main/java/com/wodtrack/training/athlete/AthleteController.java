package com.wodtrack.training.athlete;

import com.wodtrack.training.athlete.dto.AthleteResponse;
import com.wodtrack.training.athlete.dto.CreateAthleteRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping
    public ResponseEntity<AthleteResponse> createAthlete(@RequestBody @Valid CreateAthleteRequest createAthleteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(athleteService.saveAthlete(createAthleteRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteResponse> viewAthlete(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.viewAthlete(id));
    }
}
