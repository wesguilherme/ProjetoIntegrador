package com.projetointegrador.dto;

import com.projetointegrador.entity.SectionType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class SectionDto {

    private String sectionCode;
    private Double totalCapacity;
    private Double usedSpace;
    @Enumerated(EnumType.STRING)
    private SectionType sectionType;
    private Long representativeId;
    private String warehouseCode;

    public SectionDto(String sectionCode, Double totalCapacity, Double usedSpace, SectionType sectionType, Long representativeId, String warehouseCode) {
        this.sectionCode = sectionCode;
        this.totalCapacity = totalCapacity;
        this.usedSpace = usedSpace;
        this.sectionType = sectionType;
        this.representativeId = representativeId;
        this.warehouseCode = warehouseCode;
    }
}
