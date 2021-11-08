package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchStockId;
    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;
    private Long batchStockNumber;

    @ManyToOne
    @JoinColumn(name = "inbound_order_inbound_order_id")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "productSellerId")
    private ProductSeller productSeller;

    public BatchStock() {

    }



    public BatchStock(Long batchStockId, LocalDate dueDate, LocalDateTime manufacturingTime, LocalDate manufacturingDate, Integer currentQuantity, Integer initialQuantity, Float minimumTemperature, String currentTemperature, Long batchStockNumber, InboundOrder inboundOrder, ProductSeller productSeller) {
        this.batchStockId = batchStockId;
        this.dueDate = dueDate;
        this.manufacturingTime = manufacturingTime;
        this.manufacturingDate = manufacturingDate;
        this.currentQuantity = currentQuantity;
        this.initialQuantity = initialQuantity;
        this.minimumTemperature = minimumTemperature;
        this.currentTemperature = currentTemperature;
        this.batchStockNumber = batchStockNumber;
        this.inboundOrder = inboundOrder;
        this.productSeller = productSeller;
    }

    @Override
    public String toString() {
        return "{" +
                "\"batchStockId\":" + batchStockId +
                ", \"duedate\":\"" + dueDate + "\"" +
                ", \"manufacturingtime\":\"" + manufacturingTime + "\"" +
                ", \"manufacturingdate\":\"" + manufacturingDate + "\"" +
                ", \"currentquantity\":\"" + currentQuantity + "\"" +
                ", \"initialquantity\":\"" + initialQuantity + "\"" +
                ", \"minimumtemperature\":\"" + minimumTemperature + "\"" +
                ", \"currenttemperature\":\"" + currentTemperature + "\"" +
                ", \"batchStockId\":" + batchStockId +
                '}';
    }
}


