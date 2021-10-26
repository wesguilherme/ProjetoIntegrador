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

    /**
     * @param representativePersistence - é esperado um parâmetro do tipo representativePersistence para injeção de dependência
     * @author - Grupo 5 - Tester Rafael
     */
    public RepresentativeService(RepresentativePersistence representativePersistence) {
        this.representativePersistence = representativePersistence;
    }

    /**
     * @param cpf - é esperado o parametro cpf do representative
     * @return - retorna a verificaçao de duplicidade do código
     * @author - Grupo 5 - Tester Rafael
     */
    private boolean utilizedCode(String cpf) {
        Optional<Representative> val;

        val = representativePersistence.findByCpf(cpf);

        if (val.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param representative - é esperado um objeto do tipo representative
     * @return - retorna representative cadastrado na lista
     * @author - Grupo 5 - Tester Rafael
     */
    public Representative insert(Representative representative) {
        if (!utilizedCode(representative.getCpf())) {
            return representativePersistence.save(representative);
        } else {
            throw new RuntimeException("Cpf já existente");
        }
    }

    /**
     * @param id - é esperado o parametro id do representative
     * @return - retorna se o representative existe ou não através do id
     * @author - Grupo 5 - Tester Rafael
     */
    public Representative getByIdRepresentative(Long id) {
        Optional<Representative> val;

        val = representativePersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe resultado para essa busca!");
        }
    }
}


