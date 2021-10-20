package com.projetointegrador.entity;

import lombok.Data;

@Data
public class Section {

    private String sectionId;
    private String warehouseCode;
    private Double totalCapacity;
    private Enum sectionType;
    private Long representativeId;

    public Section (String sectionId, String warehouseCode, Double totalCapacity, Enum sectionType, Long representativeId) {
        this.sectionId = sectionId;
        this.warehouseCode = warehouseCode;
        this.totalCapacity = totalCapacity;
        this.sectionType = sectionType;
        this.representativeId = representativeId;
    }

    @Override
    public String toString ( ) {
        return "Section{" +
                "sectionId='" + sectionId + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", sectionType=" + sectionType +
                ", representativeId=" + representativeId +
                '}';
    }

}
