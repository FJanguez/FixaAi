package com.fixaai.domain.professional;

import com.fixaai.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record ProfessionalUpdateDTO(
        @NotNull Long id,
        String name,
        AddressDTO address
) {
}