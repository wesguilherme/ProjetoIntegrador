package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.InboundOrderDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.InboudOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundOrderService {

    @Autowired
    private InboudOrderPersistence inboudOrderPersistence;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductSellerService productSellerService;

    public InboundOrderService() {
    }

    /**
     * @param inboudOrderPersistence - é esperado um parâmetro do tipo inboundPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public InboundOrderService(InboudOrderPersistence inboudOrderPersistence) {
        this.inboudOrderPersistence = inboudOrderPersistence;
    }

    public InboundOrder insert(InboundOrderDto inboundOrderDto) {
        InboundOrder inboundOrder = convert(inboundOrderDto);
        return inboudOrderPersistence.save(inboundOrder);
    }

    public InboundOrder convert(InboundOrderDto inboundOrderDto) {

        InboundOrder in = new InboundOrder();
        in.setOrderDate(inboundOrderDto.getOrderDate());
        in.setOrderNumber(inboundOrderDto.getOrderNumber());


        in.setBatchStock(convertBatchStock(inboundOrderDto.getBatchStockDto()));

        Section sectionByCode = sectionService.getSectionByCode(inboundOrderDto.getSectionCode());
        if (sectionByCode != null) {
            in.setSection(sectionByCode);
        }

        return in;
    }

    public List<BatchStock> convertBatchStock(List<BatchStockDto> batchStockDto){
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

            ProductSeller productSeller = productSellerService.getProductSeller(item.getProductSellerId());

            bat.setProductSeller(productSeller);

            batchStock.add(bat);
        }

        return batchStock;
    }

}
