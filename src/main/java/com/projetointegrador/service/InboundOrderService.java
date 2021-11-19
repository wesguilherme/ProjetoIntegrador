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

    /**
     * @param inboundOrderPersistence é esperado um parâmetro do tipo inboundPersistence para injeção de dependência
     * @param batchStockService é esperado um parâmetro do tipo batchStockService para injeção de dependência
     */
    public InboundOrderService(InboundOrderPersistence inboundOrderPersistence, BatchStockService batchStockService) {
        this.inboundOrderPersistence = inboundOrderPersistence;
        this.batchStockService = batchStockService;
    }

    /**
     * @param inboundOrderPersistence é esperado um parâmetro do tipo inboundPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public InboundOrderService(InboundOrderPersistence inboundOrderPersistence) {
        this.inboundOrderPersistence = inboundOrderPersistence;
    }

    /**
     * @param typePersistence é esperado um parâmetro do tipo typePersistence para injeção de dependência
     * @param productPersistence é esperado um parâmetro do tipo typePersistence para injeção de dependência
     */
    public InboundOrderService(TypePersistence typePersistence, ProductPersistence productPersistence) {
        this.typePersistence = typePersistence;
        this.productPersistence = productPersistence;
    }

    /**
     * @param inboundOrder é esperado um objeto do tipo inboundOrder
     * @return um inboundOrder cadastrado
     */
    public InboundOrder insert(InboundOrder inboundOrder) {
        return inboundOrderPersistence.save(inboundOrder);
    }

    /**
     * @param inboundOrder é esperado um objeto do tipo inboundOrder
     * @param id é esperado um id do inboundOrder
     * @return uma alteração no inbound order
     */
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
     * @param initials é esperado um parâmetro do tipo initials
     * @return uma lista de produtos pelo type
     */
    public List<Product> productList(String initials) {
        Type type = typePersistence.findByInitials(initials);

        return productPersistence.findByType(type);
    }
}
