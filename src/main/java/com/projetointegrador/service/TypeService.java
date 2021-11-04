package com.projetointegrador.service;

import com.projetointegrador.entity.Type;
import com.projetointegrador.repository.TypePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypePersistence typePersistence;

    public TypeService(TypePersistence typePersistence) {
        this.typePersistence = typePersistence;
    }

    public Type getTypeByTypeId(Long id) {
        Optional<Type> val;

        val = typePersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe um tipo para o valor passado!");
        }
    }
}
