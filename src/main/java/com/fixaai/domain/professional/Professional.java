package com.fixaai.domain.professional;

import com.fixaai.domain.address.Address;
import com.fixaai.domain.assistance.Assistance;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Table(name = "professionals")
@Entity(name = "Professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean active;

    @OneToMany(mappedBy = "professional")
    private List<Assistance> assistances;

    public Professional(){
    }

    public Professional(Long id, String name, String lastName, Specialty specialty, Address address, Boolean active) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.active = active;
    }

    public Professional(Long id, String name, Specialty specialty, Boolean active) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.active = active;
    }

    public Professional(@Valid ProfessionalRegistrationDTO professionalData) {
        this.name = professionalData.name();
        this.specialty = professionalData.specialty();
        this.address = new Address(professionalData.address());
        this.active = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Professional that = (Professional) o;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getActive() {
        return active;
    }

    public void updateInformation(@Valid ProfessionalUpdateDTO professionalUpdateData) {
        Optional.ofNullable(professionalUpdateData.name()).ifPresent(name -> this.name = name);
        Optional.ofNullable(professionalUpdateData.address()).ifPresent(address -> this.address.updateInformation(address));
    }

    public void delete() {
        this.active = false;
    }
}