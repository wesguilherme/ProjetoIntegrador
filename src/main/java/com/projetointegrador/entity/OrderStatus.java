package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderStatusId;
    private String statusCode;

    public OrderStatus(Long orderStatusId, String statusCode) {
        this.orderStatusId = orderStatusId;
        this.statusCode = statusCode;
    }

    public OrderStatus ( ) {

    }

}
