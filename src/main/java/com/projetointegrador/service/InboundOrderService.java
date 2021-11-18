package com.projetointegrador.service;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Type;
import com.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InboundOrderService {

    @Autowired
    private InboundOrderPersistence inboundOrderPersistence;

    @Autowired
    private TypePersistence typePersistence;

    @Autowired
    private ProductPersistence productPersistence;

    @Autowired
    private BatchStockService batchStockService;

    public InboundOrderService() {
    }

    public InboundOrderService(InboundOrderPersistence inboundOrderPersistence, BatchStockService batchStockService) {
        this.inboundOrderPersistence = inboundOrderPersistence;
        this.batchStockService = batchStockService;
    }

    /**
     * @param inboundOrderPersistence - é esperado um parâmetro do tipo inboundPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public InboundOrderService(InboundOrderPersistence inboundOrderPersistence) {
        this.inboundOrderPersistence = inboundOrderPersistence;
    }

    public InboundOrderService(TypePersistence typePersistence, ProductPersistence productPersistence) {
        this.typePersistence = typePersistence;
        this.productPersistence = productPersistence;
    }

    public InboundOrder insert(InboundOrder inboundOrder) {
        return inboundOrderPersistence.save(inboundOrder);
    }

    public InboundOrder update(InboundOrder inboundOrder, Long id) {
        Optional<InboundOrder> inboundOrder1 = inboundOrderPersistence.findById(id);

        if (inboundOrder1.isPresent()){
            inboundOrder1.get().setOrderDate(inboundOrder.getOrderDate());
            inboundOrder1.get().setSection(inboundOrder.getSection());
            inboundOrder1.get().setOrderNumber(inboundOrder.getOrderNumber());

            List<BatchStock> batchStockList = new ArrayList<>();

            for (BatchStock item: inboundOrder.getBatchStock()) {
                Optional<BatchStock> batchStock = batchStockService.getBatchStockNumber(item.getBatchStockNumber());

                batchStock.get().setCurrentQuantity(item.getCurrentQuantity());
                batchStock.get().setInboundOrder(item.getInboundOrder());
                batchStock.get().setManufacturingTime(item.getManufacturingTime());
                batchStock.get().setInitialQuantity(item.getInitialQuantity());
                batchStock.get().setProductSeller(item.getProductSeller());
                batchStock.get().setDueDate(item.getDueDate());
                batchStock.get().setCurrentTemperature(item.getCurrentTemperature());
                batchStock.get().setMinimumTemperature(item.getMinimumTemperature());
                batchStock.get().setInboundOrder(inboundOrder1.get());

                batchStockList.add(batchStock.get());
            }

            inboundOrder1.get().setBatchStock(batchStockList);

            return inboundOrderPersistence.save(inboundOrder1.get());
        }

        throw new RuntimeException("Não existe inboundOrder com esse id!");
    }

    /**
     *
     * @param initials
     * @return
     */
    public List<Product> productList(String initials) {
        Type type = typePersistence.findByInitials(initials);

        return productPersistence.findByType(type);
    }
}
