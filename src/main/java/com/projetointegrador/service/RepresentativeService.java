package com.projetointegrador.service;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.repository.RepresentativePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativePersistence representativePersistence;

    public RepresentativeService() {
    }

    public RepresentativeService(RepresentativePersistence representativePersistence) {
        this.representativePersistence = representativePersistence;
    }

    private boolean codigoNaoUtilizado(String cpf) {
        Representative representativeExistente = representativePersistence.findByCpf(cpf);
        if(representativeExistente == null){
            return true;
        }
        return false;
    }

    public Representative cadastrar(Representative representative){
        if (codigoNaoUtilizado(representative.getCpf())) {
            return representativePersistence.save(representative);
        } else {
            throw new RuntimeException("Cpf já existente");
        }
    }
}


