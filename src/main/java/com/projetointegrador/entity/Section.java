package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Section {

    @Id
    private String sectionCode;
    private Double totalCapacity;
    private Double usedSpace;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    @ManyToOne
    @JoinColumn(name = "representativeId")
    private Representative representative;

    @ManyToOne
    @JoinColumn(name = "warehouseCode")
    private Warehouse warehouse;

    public Section() {

    }

    public Section(String sectionCode, Double totalCapacity, Double usedSpace, SectionType sectionType, Representative representative, Warehouse warehouse) {
        this.sectionCode = sectionCode;
        this.totalCapacity = totalCapacity;
        this.usedSpace = usedSpace;
        this.sectionType = sectionType;
        this.representative = representative;
        this.warehouse = warehouse;
    }
}
