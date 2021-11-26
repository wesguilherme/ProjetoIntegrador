package com.projetointegrador.service;

import com.projetointegrador.entity.States;
import com.projetointegrador.repository.StatesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatesService {

    @Autowired
    private StatesPersistence statesPersistence;

    /**
     * @param statesPersistence é esperado um parâmetro do tipo statesPersistence para injeção de dependência
     * @author Rafael
     */
    public StatesService(StatesPersistence statesPersistence) {
        this.statesPersistence = statesPersistence;
    }

    /**
     * @param states é esperado um parâmetro do tipo states
     * @return é esperado um states cadastrado
     * @author Rafael
     */
    public States insert(States states) {
        return statesPersistence.save(states);
    }

    /**
     * @param id é esperado um parâmetro do tipo id
     * @return um id existente ou lançã uma mensagem de erro
     * @author Rafael
     */
    public States getStates(String id) {
        Optional<States> val;

        val = statesPersistence.findByCep(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe um estado cadastrado!");
        }
    }
}
