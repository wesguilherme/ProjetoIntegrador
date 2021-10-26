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

    /**
     * @param inboudOrderPersistence - é esperado um parâmetro do tipo inboundPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public InboundOrderService(InboudOrderPersistence inboudOrderPersistence) {
        this.inboudOrderPersistence = inboudOrderPersistence;
    }

    /**
     * @param inboundOrder - é esperado um objeto do tipo inboundOrder
     * @return - retorna inboundOrder cadastrado na lista
     * @author - Grupo 5 - Tester Ana
     */
    public InboundOrder insert(InboundOrder inboundOrder) {
        return inboudOrderPersistence.save(inboundOrder);
    }

}
