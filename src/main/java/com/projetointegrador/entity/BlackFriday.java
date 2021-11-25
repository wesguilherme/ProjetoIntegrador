package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlackFriday {

    @Id
    private String productId;

    @NotNull
    private BigDecimal discount;

    @NotNull
    private LocalDate initialDate;

    @NotNull
    private LocalDate finalDate;
}
