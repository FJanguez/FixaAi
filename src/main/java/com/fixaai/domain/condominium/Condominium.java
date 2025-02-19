package com.fixaai.domain.condominium;

import com.fixaai.domain.address.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Objects;
import java.util.Optional;

@Table(name = "condominiums")
@Entity(name = "Condominium")
public class Condominium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Address address;

    private Boolean active;

    public Condominium() {
    }

    public Condominium(Long id, String name, Address address, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.active = active;
    }

    public Condominium(@Valid CondominiumRegistrationDTO condominiumData) {
        this.name = condominiumData.name();
        this.address = new Address(condominiumData.address());
        this.active = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Condominium that = (Condominium) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getActive() {
        return active;
    }

    public void updateInformation(@Valid CondominiumUpdateDTO condominiumUpdateData) {
        Optional.ofNullable(condominiumUpdateData.name()).ifPresent(name -> this.name = name);
    }

    public void delete() {
        this.active = false;
    }
}
