package com.fixaai.domain.condominium;

import com.fixaai.domain.address.Address;

public record CondominiumDetailingDTO (Long id, String nome, Address address){

    public CondominiumDetailingDTO(Condominium condominium) {
        this(condominium.getId(), condominium. getName(), condominium.getAddress());
    }
}
