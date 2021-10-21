package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Representative extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String representativeId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> Addresses;

    public Representative() {

    }

    public Representative(String cpf, String name, String representativeId, List<Address> addresses) {
        super(cpf, name);
        this.representativeId = representativeId;
        Addresses = addresses;
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
