package com.wodtrack.training.athlete.dto;

import com.wodtrack.training.athlete.Athlete;

public record AthleteResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
    public static AthleteResponse from(Athlete athlete) {
        return new AthleteResponse(athlete.getId(), athlete.getFirstName(),
                athlete.getLastName(), athlete.getEmail());
    }
}
