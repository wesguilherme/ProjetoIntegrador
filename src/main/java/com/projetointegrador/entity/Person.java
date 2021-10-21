package com.projetointegrador.entity;

import lombok.Data;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Data
public class Person {

    private String cpf;
    private String name;
    private Integer addressId;

    public Person() {

    }

    public Person(String cpf, String name, Integer addressId) {
        this.cpf = cpf;
        this.name = name;
        this.addressId = addressId;
    }

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + name + '\'' +
                ", sobrenome='" + addressId + '\'' +
                '}';
    }

}
