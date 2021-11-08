package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    private String initials;
    private String environmentType;

    public Type() {

    }

    public Type(Long typeId, String initials, String environmentType) {
        this.typeId = typeId;
        this.initials = initials;
        this.environmentType = environmentType;
    }

//    public Type (long l, String rf, String refrigerados) {
//    }
}
