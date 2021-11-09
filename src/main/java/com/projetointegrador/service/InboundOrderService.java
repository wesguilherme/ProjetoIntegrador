package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.InboundOrderDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.InboundOrderPersistence;
import com.projetointegrador.repository.ProductPersistence;
import com.projetointegrador.repository.TypePersistence;
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
}
