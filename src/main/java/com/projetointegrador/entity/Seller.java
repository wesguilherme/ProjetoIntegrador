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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> Addresses;

    public Seller(){

    }

    public Seller(Long sellerId, String cpf, String name, List<Address> addresses) {
        this.sellerId = sellerId;
        this.cpf = cpf;
        this.name = name;
        Addresses = addresses;
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