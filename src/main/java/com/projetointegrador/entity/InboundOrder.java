package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inboundOrderId;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "sectionCode")
    private Section section;

    @OneToMany(mappedBy = "inboundOrder" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;

    public InboundOrder() {

    }

    public InboundOrder(Long inboundOrderId, Integer orderNumber, LocalDate orderDate, Section section, BatchStock batchStock) {
        this.inboundOrderId = inboundOrderId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.section = section;
    }

    @Override
    public String toString() {
        return "{" +
                " \"inboundOrderId\": " + inboundOrderId +
                ", \"orderNumber\":" + orderNumber +
                ", \"orderDate\":" + orderDate +
                ", \"section\":" + section +
                "}";
    }
}
