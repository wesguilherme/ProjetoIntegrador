package com.projetointegrador.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchStockResponseDto {

    private SectionResponseDto sectionResponseDto;
    private String productId;
    private List<BatchStockList> batchStockList;

    public BatchStockResponseDto() {
    }
}
