package com.projetointegrador.dto;

import com.projetointegrador.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto {

    @NotNull
    private LocalDate date;
    @NotNull
    private Long buyerId;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private List<ProductItemDto> products;

}
