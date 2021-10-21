package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Data
public class Person {

    private String cpf;
    private String name;

    public Person() {

    }

    public Person(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

}