package com.projetointegrador.dto;

import com.projetointegrador.entity.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
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
