package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String number;
    private String postalCode;
    private String city;
    private String state;
    private String complement;

    @Override
    public String toString ( ) {
        return "{" +
                " street : '" + street + '\'' +
                ", number:'" + number + '\'' +
                ", postalCode:'" + postalCode + '\'' +
                ", city:'" + city + '\'' +
                ", state:'" + state + '\'' +
                ", complement:'" + complement + '\'' +
                '}';
    }

}
