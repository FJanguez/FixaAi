package com.fixaai.domain.professional;

import com.fixaai.domain.address.AddressDTO;
import jakarta.validation.Valid;

public record ProfessionalRegistrationDTO(
        String name,

        Specialty specialty,

        @Valid
        AddressDTO address

) {
}
