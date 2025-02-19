package com.fixaai.domain.condominium;

import com.fixaai.domain.address.AddressDTO;
import jakarta.validation.Valid;

public record CondominiumRegistrationDTO(
        String name,

        @Valid
        AddressDTO address
) {
}
