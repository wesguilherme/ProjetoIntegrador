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

@Service
public class InboundOrderService {

    @Autowired
    private InboundOrderPersistence inboundOrderPersistence;

    @Autowired
    private TypePersistence typePersistence;

    @Autowired
    private ProductPersistence productPersistence;

    @Autowired
    private WarehousePersistence warehousePersistence;

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    public InboundOrderService() {
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

    public List<Product> productList(String initials) {
        Type type = typePersistence.findByInitials(initials);

        return productPersistence.findByType(type);
    }

    public Product WarehouseProductList(String id) {
        Product product = productPersistence.findByProductId(id);
        return productPersistence.findByProductId(String.valueOf(product));
    }

    public List<BatchStockPersistence.BatchStockListByDays> batchStockInSection(String sectionCode, Integer quantityOfDays) {
        List<BatchStockPersistence.BatchStockListByDays> batchStockListByDays = batchStockPersistence.listbatchByDays(sectionCode, quantityOfDays);
        return batchStockListByDays;
    }

    public List<BatchStock> batchStockListWithFilter(Integer quantityOfDays, String initials, String classification) {
        return null;
    }
}
