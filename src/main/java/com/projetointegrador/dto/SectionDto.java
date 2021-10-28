package com.projetointegrador.dto;

import lombok.Data;

@Data
public class SectionDto {

    private String sectionCode;
    private Double totalCapacity;
    private Long typeId;
    private Long representativeId;
    private String warehouseCode;

    public SectionDto(String sectionCode, Double totalCapacity, Long typeId, Long representativeId, String warehouseCode) {
        this.sectionCode = sectionCode;
        this.totalCapacity = totalCapacity;
        this.typeId = typeId;
        this.representativeId = representativeId;
        this.warehouseCode = warehouseCode;
    }
}
