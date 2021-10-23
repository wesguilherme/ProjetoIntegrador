package com.projetointegrador.service;

import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.repository.InboudOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboundOrderService {

    @Autowired
    private InboudOrderPersistence inboudOrderPersistence;

    public InboundOrderService() {
    }

    public InboundOrderService(InboudOrderPersistence inboudOrderPersistence) {
        this.inboudOrderPersistence = inboudOrderPersistence;
    }

    public InboundOrder cadastrar(InboundOrder inboundOrder){
        return inboudOrderPersistence.save(inboundOrder);
    }

}
