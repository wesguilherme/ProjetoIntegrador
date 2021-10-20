package com.projetointegrador.entity;

import lombok.Data;

@Data
public class Address {

    private int addressID;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private String state;
    private String complement;

    public Address (int addressID, String street, String number, String postalCode, String city, String state, String complement) {
        this.addressID = addressID;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    @Override
    public String toString ( ) {
        return "Address{" +
                "addressID=" + addressID +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }

}
