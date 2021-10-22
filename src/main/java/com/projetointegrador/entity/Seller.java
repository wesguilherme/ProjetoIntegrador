package com.projetointegrador.entity;
import lombok.Data;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

@Data
@Entity
public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    private String cpf;
    private String name;

    @Embedded
    private Address address;

    public Seller(){

    }

    public Seller(Long sellerId, String cpf, String name, Address address) {
        this.sellerId = sellerId;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

    // @Override
//    public String toString() {
//        return "Seller{" +
//                " \"name\":" + super.getName() + "\"" +
//                ", \"cpf\":\"" + super.getCpf() + "\"" +
//                ", \"sellerId\":" + sellerId +
//                ", \"address\":" + address +
//                '}';
//    }
}