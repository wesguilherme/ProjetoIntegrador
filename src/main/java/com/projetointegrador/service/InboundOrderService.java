package com.projetointegrador.service;

import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Type;
import com.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @author - Grupo 5
     */
    public InboundOrderService(InboundOrderPersistence inboundOrderPersistence) {
        this.inboundOrderPersistence = inboundOrderPersistence;
    }

    /**
     * @param typePersistence  é esperado um parâmetro do tipo typePersistence para injeção de dependência
     * @param productPersistence é esperado um parâmetro do tipo productPersistence para injeção de dependência
     * @author - Grupo 5
     */
    public InboundOrderService(TypePersistence typePersistence, ProductPersistence productPersistence) {
        this.typePersistence = typePersistence;
        this.productPersistence = productPersistence;
    }

    /**
     * @param inboundOrder é esperado o objeto do tipo inboundOrder
     * @return inboundOrder cadastrado
     * @author - Grupo 5
     */
    public InboundOrder insert(InboundOrder inboundOrder) {
        return inboundOrderPersistence.save(inboundOrder);
    }

    /**
     * @param initials é esperado o parâmetro initials do product
     * @return lista de product
     */
    public List<Product> productList(String initials) {
        Type type = typePersistence.findByInitials(initials);

        return productPersistence.findByType(type);
    }
//
//    public Product WarehouseProductList(String id) {
//        Product product = productPersistence.findByProductId(id);
//        return productPersistence.findByProductId(String.valueOf(product));
//    }
}
