package com.fixaai.domain.assistance;

import com.fixaai.domain.professional.Specialty;

import java.time.LocalDateTime;

public record AssistanceListingDTO(Long id,
                                   Long condominiumId,
                                   Long professionalId,
                                   LocalDateTime scheduledDate,
                                   AssistanceStatus status
                                   ) {

    public AssistanceListingDTO(Assistance assistance){
        this(
                assistance.getId(),
                assistance.getCondominium().getId(),
                assistance.getProfessional().getId(),
                assistance.getScheduledDate(),
                assistance.getStatus()
                );
    }
}
