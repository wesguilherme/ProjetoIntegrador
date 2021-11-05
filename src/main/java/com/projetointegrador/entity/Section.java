package com.projetointegrador.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class Section {

    @Id
    private String sectionCode;
    private Double totalCapacity;
    private Double usedSpace;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "representativeId")
    private Representative representative;

    @ManyToOne
    @JoinColumn(name = "warehouseCode")
    private Warehouse warehouse;

    public Section() {

    }

    public Section(String sectionCode, Double totalCapacity, Double usedSpace, Type type, Representative representative, Warehouse warehouse) {
        this.sectionCode = sectionCode;
        this.totalCapacity = totalCapacity;
        this.usedSpace = usedSpace;
        this.type = type;
        this.representative = representative;
        this.warehouse = warehouse;
    }

}
