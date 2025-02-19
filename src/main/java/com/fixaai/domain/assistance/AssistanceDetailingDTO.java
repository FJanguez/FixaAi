package com.fixaai.domain.assistance;

import java.time.LocalDateTime;

public record AssistanceDetailingDTO(Long id,
                                     Long idProfessional,
                                     Long idCondominium,
                                     String description,
                                     LocalDateTime scheduledDate,
                                     AssistanceStatus status,
                                     LocalDateTime completionDate,
                                     LocalDateTime cancellationDate,
                                     CancellationReason cancellationReason
) {

    public AssistanceDetailingDTO(Assistance assistance){
        this(assistance.getId(),
                assistance.getProfessional().getId(),
                assistance.getCondominium().getId(),
                assistance.getDescription(),
                assistance.getScheduledDate(),
                assistance.getStatus(),
                assistance.getCompletionDate(),
                assistance.getCancellationDate(),
                assistance.getCancellationReason()
                );
    }

}