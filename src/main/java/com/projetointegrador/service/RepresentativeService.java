package com.projetointegrador.service;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.repository.RepresentativePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativePersistence representativePersistence;

    public RepresentativeService() {
    }

    public RepresentativeService(RepresentativePersistence representativePersistence) {
        this.representativePersistence = representativePersistence;
    }

    private boolean utilizedCode(String cpf) {
        Optional<Representative> val;

        val = representativePersistence.findByCpf(cpf);

        if (val.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public Representative insert(Representative representative){
        if (!utilizedCode(representative.getCpf())) {
            return representativePersistence.save(representative);
        } else {
            throw new RuntimeException("Cpf já existente");
        }
    }

    public Representative getByIdRepresentative(Long id){
        Optional<Representative> val;

        val = representativePersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe resultado para essa busca!");
        }
    }
}


