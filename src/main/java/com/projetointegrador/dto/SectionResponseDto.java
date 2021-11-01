package com.projetointegrador.dto;


import lombok.Data;

@Data
public class SectionResponseDto {

    private String sectionCode;
    private String warehouseCode;

    public SectionResponseDto() {
    }

    public SectionResponseDto(String sectionCode, String warehouseCode) {
        this.sectionCode = sectionCode;
        this.warehouseCode = warehouseCode;
    }
}
