package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseItemId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchaseOrderId")
    private PurchaseOrder purchaseOrder;
}
