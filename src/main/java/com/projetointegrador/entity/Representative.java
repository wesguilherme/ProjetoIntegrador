package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

@Data
@Entity
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long representativeId;

    @NotNull @NotBlank
    private String cpf;

    @NotNull @NotBlank
    private String name;

    @Embedded
    private Address address;

    public Representative() {

    }

    public Representative(Long representativeId, String cpf, String name, Address address) {
        this.representativeId = representativeId;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
    }

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

    //    @Override
//    public String toString() {
//        return "Seller{" +
//                " \"nome\":" + super.getName() + "\"" +
//                ", \"cpf\":\"" + super.getCpf() + "\"" +
//                ", \"endereço\":\"" + super.getAddressId() + "\"" +
//                "representativeId='" + representativeId + "\"" +
//                '}';
//    }
}
