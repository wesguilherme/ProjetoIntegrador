package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

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
}
