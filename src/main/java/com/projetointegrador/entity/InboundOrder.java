package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inboundOrderId;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_code")
    private Section section;

    @OneToMany(mappedBy = "inboundOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;


    @Override
    public String toString() {
        return "{" +
                " \"inboundOrderId\": " + inboundOrderId +
                ", \"orderNumber\":" + orderNumber +
                ", \"orderDate\":" + orderDate +
                ", \"section\":" + section +
                "}";
    }
}
