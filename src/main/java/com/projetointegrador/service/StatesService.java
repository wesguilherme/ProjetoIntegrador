package com.projetointegrador.service;

import com.projetointegrador.dto.StateDto;
import com.projetointegrador.entity.States;
import com.projetointegrador.repository.StatesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatesService {

    @Autowired
    private StatesPersistence statesPersistence;

    public StatesService() {}


    public States insert(States states) {
        return statesPersistence.save(states);
    }

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
