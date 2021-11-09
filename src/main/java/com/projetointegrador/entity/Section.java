package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

}
