package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sectionId;
    private Double totalCapacity;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    private Long representativeId;

    @ManyToOne
    private Warehouse warehouse;

    public Section() {

    }

    public Section(String sectionId, Double totalCapacity, SectionType sectionType, Long representativeId, Warehouse warehouse) {
        this.sectionId = sectionId;
        this.totalCapacity = totalCapacity;
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
