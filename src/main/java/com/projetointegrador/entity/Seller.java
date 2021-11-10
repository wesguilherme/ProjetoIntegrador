package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String name;

    @Embedded
    private Address address;

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
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

    @Override
    public String toString() {
        return "{" +
                " \"name\":" + name + "\"" +
                ", \"cpf\":\"" + cpf + "\"" +
                ", \"sellerId\":" + sellerId +
                ", \"address\":" + address +
                '}';
    }
}