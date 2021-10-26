package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Data
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;
    private String cpf;
    private String name;

    @Embedded
    private Address address;

    public Buyer ( ) {
    }

    public Buyer (Long buyerId, String cpf, String name, Address address) {
        this.buyerId = buyerId;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
    }

    public String validaCpf (String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

    @Override
    public String toString ( ) {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

