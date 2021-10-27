package com.projetointegrador.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InboundOrderDto {

    private Long inboundOrderId;
    private Integer orderNumber;
    private LocalDate orderDate;
    private String sectionCode;
    private List<BatchStockDto> batchStockDto;

}
