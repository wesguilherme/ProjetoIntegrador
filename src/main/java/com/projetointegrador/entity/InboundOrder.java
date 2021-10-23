package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inboundOrderId;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Section section;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;

    public InboundOrder() {

    }

    public InboundOrder(Long inboundOrderId, Integer orderNumber, LocalDate orderDate, Section section, List<BatchStock> batchStock) {
        this.inboundOrderId = inboundOrderId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.section = section;
        this.batchStock = batchStock;
    }

    @Override
    public String toString() {
        return "{" +
                " \"inboundOrderId\": " + inboundOrderId +
                ", \"orderNumber\":" + orderNumber +
                ", \"orderDate\":" + orderDate +
                ", \"section\":" + section +
                ", \"batchStock\":" + batchStock +
                "}";
    }
}
