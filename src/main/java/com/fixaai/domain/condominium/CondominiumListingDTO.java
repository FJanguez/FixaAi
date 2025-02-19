package com.fixaai.domain.condominium;

public record CondominiumListingDTO(Long id, String name) {

    public CondominiumListingDTO(Condominium condominium){
        this(condominium.getId(), condominium.getName());
    }

}
