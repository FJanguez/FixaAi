package com.fixaai.domain.assistance;

import com.fixaai.domain.professional.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AssistanceScheduleDTO(
        Long idProfessional,

        @NotNull
        Long idCondominium,

        @NotBlank
        String description,

        @NotNull @Future
        LocalDateTime assistanceDate,

        Specialty specialty
) {

    public AssistanceScheduleDTO(Assistance assistance) {
        this(
                assistance.getProfessional().getId(),
                assistance.getCondominium().getId(),
                assistance.getDescription(),
                assistance.getScheduledDate(),
                assistance.getSpecialty()
                );
    }
}