package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private Double totalCapacity;
    private Double usedSpace;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    private Long representativeId;

    @ManyToOne
    private Warehouse warehouse;

    public Section() {
    }

    public Section(Long sectionId, Double totalCapacity, Double usedSpace, SectionType sectionType, Long representativeId, Warehouse warehouse) {
        this.sectionId = sectionId;
        this.totalCapacity = totalCapacity;
        this.usedSpace = usedSpace;
        this.sectionType = sectionType;
        this.representativeId = representativeId;
        this.warehouse = warehouse;
    }

    @Override
    public String toString ( ) {
        return "Section{" +
                "sectionId='" + sectionId + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", sectionType=" + sectionType +
                ", representativeId=" + representativeId +
                '}';
    }

}
