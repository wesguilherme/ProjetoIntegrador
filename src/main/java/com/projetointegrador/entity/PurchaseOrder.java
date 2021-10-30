package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderId;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Buyer bayer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseItem> purchaseItems;

}
