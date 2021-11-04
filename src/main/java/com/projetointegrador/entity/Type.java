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

}
