package com.fixaai.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Optional;

@Embeddable
public class Address {

    @Column(name = "zip_code")
    private String zipCode;
    private Integer number;
    private String street;
    private String neighborhood;
    private String city;

    @Enumerated(EnumType.STRING)
    private BrazilState state;

    @Column(name = "address_complement")
    private String addressComplement;

    @Column(name = "reference_point")
    private String referencePoint;

    public Address() {

    }

    public Address(String zipCode, Integer number, String street, String neighborhood, String city, BrazilState state, String addressComplement, String referencePoint) {
        this.zipCode = zipCode;
        this.number = number;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.addressComplement = addressComplement;
        this.referencePoint = referencePoint;
    }

    public Address(AddressDTO addressData) {
        this.zipCode = addressData.zipCode();
        this.number = addressData.number();
        this.street = addressData.street();
        this.neighborhood = addressData.neighborhood();
        this.city = addressData.city();
        this.state = addressData.state();
        this.addressComplement = addressData.addressComplement();
        this.referencePoint = addressData.referencePoint();
    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public BrazilState getState() {
        return state;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void updateInformation(AddressDTO addressData) {
        Optional.ofNullable(addressData.zipCode()).ifPresent(zipCode -> this.zipCode = zipCode);
        Optional.ofNullable(addressData.number()).ifPresent(number -> this.number = number);
        Optional.ofNullable(addressData.street()).ifPresent(street -> this.street = street);
        Optional.ofNullable(addressData.neighborhood()).ifPresent(neighborhood -> this.neighborhood = neighborhood);
        Optional.ofNullable(addressData.city()).ifPresent(city -> this.city = city);
        Optional.ofNullable(addressData.state()).ifPresent(state -> this.state = state);
        Optional.ofNullable(addressData.addressComplement()).ifPresent(addressComplement -> this.addressComplement = addressComplement);
        Optional.ofNullable(addressData.referencePoint()).ifPresent(referencePoint -> this.referencePoint = referencePoint);
    }
}
