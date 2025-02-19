package com.fixaai.domain.assistance;

import jakarta.persistence.Enumerated;

public record AssistanceCancelDTO(

        Long idAssistance,

        CancellationReason cancellationReason
) {

}