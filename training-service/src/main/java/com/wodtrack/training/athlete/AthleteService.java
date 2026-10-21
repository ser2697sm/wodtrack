package com.wodtrack.training.athlete;

import com.wodtrack.training.athlete.dto.AthleteResponse;
import com.wodtrack.training.athlete.dto.CreateAthleteRequest;
import com.wodtrack.training.common.exception.ConflictException;
import com.wodtrack.training.common.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public AthleteResponse saveAthlete(CreateAthleteRequest createAthleteRequest) {

        String email = createAthleteRequest.email().trim().toLowerCase();

        if(athleteRepository.existsByEmail(email)) {
            throw new ConflictException("El email ya existe");
        }

        Athlete athlete = Athlete.builder()
                .firstName(createAthleteRequest.firstName())
                .lastName(createAthleteRequest.lastName())
                .email(email)
                .build();

        Athlete saved = athleteRepository.save(athlete);

        return AthleteResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public AthleteResponse viewAthlete(Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Atleta no encontrado con id " + id)
        );

        return AthleteResponse.from(athlete);
    }

}
