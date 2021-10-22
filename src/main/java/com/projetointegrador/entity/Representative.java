package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

@Data
@Entity
public class Representative{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long representativeId;
    private String cpf;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> Addresses;

    public Representative() {

    }

    public Representative(Long representativeId, String cpf, String name, List<Address> addresses) {
        this.representativeId = representativeId;
        this.cpf = cpf;
        this.name = name;
        Addresses = addresses;
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
