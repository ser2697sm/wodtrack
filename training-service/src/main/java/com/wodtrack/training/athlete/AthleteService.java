package com.wodtrack.training.athlete;

import com.wodtrack.training.athlete.dto.AthleteResponse;
import com.wodtrack.training.athlete.dto.CreateAthleteRequest;
import com.wodtrack.training.common.exception.ConflictException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public AthleteResponse saveAthete(CreateAthleteRequest createAthleteRequest) {

        Athlete athlete = Athlete.builder()
                .firstName(createAthleteRequest.firstName())
                .lastName(createAthleteRequest.lastName())
                .email(createAthleteRequest.email())
                .createdAt(OffsetDateTime.now())
                .build();

        athleteRepository.save(athlete);

        return new AthleteResponse(createAthleteRequest.firstName(),
                createAthleteRequest.lastName(),
                createAthleteRequest.email());
    }

    @Transactional
    public AthleteResponse viewAthete(Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow(() -> {
            throw new ConflictException("Atleta no encontrado con id " + id);
        });

        return new AthleteResponse(athlete.getFirstName(),
                athlete.getLastName(),
                athlete.getEmail());

    }

}
