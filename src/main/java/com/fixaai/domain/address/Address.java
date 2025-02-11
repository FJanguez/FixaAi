package com.fixaai.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "zip_code")
    private String zipCode;
    private Integer number;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    @Column(name = "address_complement")
    private String addressComplement;

    @Column(name = "reference_point")
    private String referencePoint;


}
