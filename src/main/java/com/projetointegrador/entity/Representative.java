package com.projetointegrador.entity;

import lombok.Data;

@Data
public class Representative extends Person{

    private String representativeId;
    private String name;
    private Integer addressId;

    public Representative(String cpf, String name, Integer addressId, String representativeId) {
        super(cpf, name, addressId);
        this.representativeId = representativeId;
        this.name = name;
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Seller{" +
                " \"nome\":" + super.getName() + "\"" +
                ", \"cpf\":\"" + super.getCpf() + "\"" +
                ", \"endereço\":\"" + super.getAddressId() + "\"" +
                "representativeId='" + representativeId + "\"" +
                '}';
    }
}
