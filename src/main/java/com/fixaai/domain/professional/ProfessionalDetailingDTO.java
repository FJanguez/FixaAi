package com.fixaai.domain.professional;

import com.fixaai.domain.address.Address;
import com.fixaai.domain.condominium.Condominium;

public record ProfessionalDetailingDTO(Long id, String name, Specialty specialty, Address address){

    public ProfessionalDetailingDTO(Professional professional) {
        this(professional.getId(), professional.getName(), professional.getSpecialty(), professional.getAddress());
    }
}
