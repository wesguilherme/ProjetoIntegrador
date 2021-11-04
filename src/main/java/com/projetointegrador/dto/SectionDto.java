package com.projetointegrador.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SectionDto {

    @NotNull
    @NotBlank
    private String sectionCode;
    @NotNull
    private Double totalCapacity;
    @NotNull
    private Long typeId;
    @NotNull
    private Long representativeId;
    @NotNull
    @NotBlank
    private String warehouseCode;

    public SectionDto(String sectionCode, Double totalCapacity, Long typeId, Long representativeId, String warehouseCode) {
        this.sectionCode = sectionCode;
        this.totalCapacity = totalCapacity;
        this.typeId = typeId;
        this.representativeId = representativeId;
        this.warehouseCode = warehouseCode;
    }
}
