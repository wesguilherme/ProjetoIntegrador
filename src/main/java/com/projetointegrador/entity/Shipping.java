package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {

    @Id
    private String shippingId;

    @ManyToOne
    @JoinColumn(name = "warehouseCode")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cep")
    private States states;

}
