package com.wodtrack.training.athlete.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAthleteRequest(
        @NotNull(message = "El nombre es obligatoria")
        String firstName,
        @NotNull(message = "El apellido es obligatoria")
        String lastName,
        @NotNull(message = "El email es obligatoria")
        @Email(message = "El formato del email no es válido")
        @Size(max = 255, message = "El email no puede superar los 255 caracteres")
        String email
) {}
