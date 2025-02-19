package com.fixaai.domain.condominium;

import jakarta.validation.constraints.NotNull;

public record CondominiumUpdateDTO(
        @NotNull Long id,
        String name) {
}