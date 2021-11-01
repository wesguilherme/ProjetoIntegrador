package com.projetointegrador.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class InboundOrderDto {

    @NotNull
    private Integer orderNumber;
    @NotNull
    private LocalDate orderDate;
    @NotNull
    @NotBlank
    private String sectionCode;
    private List<BatchStockDto> batchStockDto;

}
