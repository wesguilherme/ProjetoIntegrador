package com.projetointegrador.dto;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.entity.Section;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDto {

    @NotNull
    private Integer orderNumber;
    @NotNull
    private LocalDate orderDate;
    @NotNull
    @NotBlank
    private String sectionCode;
    private List<BatchStockDto> batchStockDto;

    public InboundOrder convert(InboundOrderDto inboundOrderDto, SectionService sectionService, ProductSellerService productSellerService) {
        InboundOrder in = new InboundOrder();
        in.setOrderDate(inboundOrderDto.getOrderDate());
        in.setOrderNumber(inboundOrderDto.getOrderNumber());

        in.setBatchStock(convertBatchStock(inboundOrderDto.getBatchStockDto(), in, productSellerService));

        Section sectionByCode = sectionService.getSectionByCode(inboundOrderDto.getSectionCode());

        for (BatchStockDto item : inboundOrderDto.getBatchStockDto()) {
            sectionService.verifyEqualType(sectionByCode.getType().getEnvironmentType(), item.getProductSellerId());
        }

        sectionService.verifyAvailableSpace(sectionByCode, inboundOrderDto.getBatchStockDto());

        if (sectionByCode != null) {
            in.setSection(sectionByCode);
        }

        return in;
    }

    private List<BatchStock> convertBatchStock(List<BatchStockDto> batchStockDto, InboundOrder inboundOrder, ProductSellerService productSellerService){
        List<BatchStock> batchStock = new ArrayList<>();

        for (BatchStockDto item : batchStockDto) {
            BatchStock bat = new BatchStock();
            bat.setDueDate(item.getDueDate());
            bat.setCurrentQuantity(item.getCurrentQuantity());
            bat.setCurrentTemperature(item.getCurrentTemperature());
            bat.setManufacturingDate(item.getManufacturingDate());
            bat.setMinimumTemperature(item.getMinimumTemperature());
            bat.setInitialQuantity(item.getInitialQuantity());
            bat.setManufacturingTime(item.getManufacturingTime());
            bat.setBatchStockNumber(item.getBatchStockNumber());

            ProductSeller productSeller = productSellerService.getProductSeller(item.getProductSellerId());

            bat.setProductSeller(productSeller);
            bat.setInboundOrder(inboundOrder);
            batchStock.add(bat);
        }

        return batchStock;
    }
}
