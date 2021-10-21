package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumberId;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BatchStock batchStock;

    public InboundOrder() {

    }

    public InboundOrder(Integer orderNumberId, Integer orderNumber, LocalDate orderDate, Section section, BatchStock batchStock) {
        this.orderNumberId = orderNumberId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.section = section;
        this.batchStock = batchStock;
    }

    @Override
    public String toString() {
        return "{" +
                " \"orderNumberId\": " + orderNumberId +
                ", \"orderNumber\":" + orderNumber +
                ", \"orderDate\":" + orderDate +
                ", \"section\":" + section +
                ", \"batchStock\":" + batchStock +
                "}";
    }
}
