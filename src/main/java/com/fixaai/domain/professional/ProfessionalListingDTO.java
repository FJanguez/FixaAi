package com.fixaai.domain.professional;

import com.fixaai.domain.condominium.Condominium;

public record ProfessionalListingDTO(Long id, String name, Specialty specialty) {

    public ProfessionalListingDTO(Professional professional) {
        this(professional.getId(), professional.getName(), professional.getSpecialty());
    }

}
