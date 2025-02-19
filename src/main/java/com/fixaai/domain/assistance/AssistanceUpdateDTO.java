package com.fixaai.domain.assistance;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AssistanceUpdateDTO(
        @NotNull Long id,
        String description,
        LocalDateTime scheduledDate
        ) {
}
