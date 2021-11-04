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

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private Buyer bayer;

    @ManyToOne
    @JoinColumn(name = "orderStatusId")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PurchaseItem> purchaseItems;
}
