package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> product;

    public BatchStock() {
    }

    public BatchStock(Long batchId, LocalDate dueDate, LocalDateTime manufacturingTime, LocalDate manufacturingDate, Integer currentQuantity, Integer initialQuantity, Float minimumTemperature, String currentTemperature, List<Product> product) {
        this.batchId = batchId;
        this.dueDate = dueDate;
        this.manufacturingTime = manufacturingTime;
        this.manufacturingDate = manufacturingDate;
        this.currentQuantity = currentQuantity;
        this.initialQuantity = initialQuantity;
        this.minimumTemperature = minimumTemperature;
        this.currentTemperature = currentTemperature;
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" +
                "\"batchid\":" + batchId +
                ", \"duedate\":\"" + dueDate + "\"" +
                ", \"manufacturingtime\":\"" + manufacturingTime + "\"" +
                ", \"manufacturingdate\":\"" + manufacturingDate + "\"" +
                ", \"currentquantity\":\"" + currentQuantity + "\"" +
                ", \"initialquantity\":\"" + initialQuantity + "\"" +
                ", \"minimumtemperature\":\"" + minimumTemperature + "\"" +
                ", \"currenttemperature\":\"" + currentTemperature + "\"" +
                ", \"product\":" + product +
                '}';
    }
}


