package com.projetointegrador.entity;
import lombok.Data;

@Data
public class Seller extends Person{

    private String sellerId;
    private Integer personId;
    private Integer addressId;

    public Seller(String cpf, String name, Integer address, String sellerId, Integer personId, Integer addressId) {
        super(cpf, name, address);
        this.sellerId = sellerId;
        this.personId = personId;
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Seller{" +
                " \"nome\":" + super.getName() + "\"" +
                ", \"cpf\":\"" + super.getCpf() + "\"" +
                ", \"endereço\":\"" + super.getAddressId() + "\"" +
                "sellerId='" + sellerId + "\"" +
                ", personId=" + personId + "\"" +
                ", addressId=" + addressId + "\"" +
                '}';
    }
}
