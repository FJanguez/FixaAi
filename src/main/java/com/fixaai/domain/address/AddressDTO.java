package com.fixaai.domain.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(
        @NotNull(message = "Zip code cannot be null.")
        @Pattern(regexp = "^[0-9]{8}$", message = "Invalid zip code. Expected format: XXXXXXXX.")
        String zipCode,

        @NotNull(message = "Number cannot be null.")
        Integer number,

        @NotNull(message = "Street cannot be null.")
        @Size(min = 3, max = 100, message = "Street must be between 3 and 100 characters.")
        String street,

        @NotNull(message = "Neighborhood cannot be null.")
        @Size(min = 3, max = 100, message = "Neighborhood must be between 3 and 100 characters.")
        String neighborhood,

        @NotNull(message = "City cannot be null.")
        @Size(min = 3, max = 100, message = "City must be between 3 and 100 characters.")
        String city,

        @NotNull(message = "State cannot be null.")
        BrazilState state,

        @Size(max = 100, message = "Address complement cannot exceed 100 characters.")
        String addressComplement,

        @Size(max = 100, message = "Reference point cannot exceed 100 characters.")
        String referencePoint
) {
}
