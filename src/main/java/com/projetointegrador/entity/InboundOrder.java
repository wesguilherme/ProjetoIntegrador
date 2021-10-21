package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;

@Entity
@Data
public class InboundOrder {

    @Id
    private Integer orderNumberId;
    private Integer orderNumber;
    private LocalDate orderDate;

    @OneToMany
    private Section section;

    @OneToMany
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
