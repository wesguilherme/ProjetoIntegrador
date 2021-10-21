package com.projetointegrador.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Seller extends Person{

    @Id
    private String sellerId;

    @OneToMany(mappedBy = "Seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> Addresses;

    public Seller(){
    }

    public Seller(String cpf, String name, String sellerId, List<Address> addresses) {
        super(cpf, name);
        this.sellerId = sellerId;
        Addresses = addresses;
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