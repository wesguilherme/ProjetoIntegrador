package com.projetointegrador.service;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.entity.Section;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.RepresentativePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativePersistence representativePersistence;

    @Autowired
    private SectionService sectionService;

    public RepresentativeService() {
    }

    /**
     * @param representativePersistence - é esperado um parâmetro do tipo representativePersistence para injeção de dependência
     * @author - Grupo 5
     */
    public RepresentativeService(RepresentativePersistence representativePersistence) {
        this.representativePersistence = representativePersistence;
    }

    /**
     * @param cpf - é esperado o parametro cpf do representative
     * @return - retorna a verificaçao de duplicidade do código
     * @author - Grupo 5
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
     * @author - Grupo 5
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
     * @author - Grupo 5
     */
    public Representative getByIdRepresentative(Long id) {
        Optional<Representative> val;

        val = representativePersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Representative para essa busca!");
        }
    }

    /**
     *
     * @param representativeId - é esperado o parametro representativeId
     * @return retorna se o representante pertence ao armazem
     * @author - Grupo 5
     */
    public boolean verifyRepresentativeBelongsToWarehouse(Long representativeId){
        Section section1 = sectionService.getRepresentative(representativeId);
        if (section1 != null){
            return true;
        } else {
            throw new RuntimeException("Não existe armazem vinculado a esse representante");
        }
    }
}


