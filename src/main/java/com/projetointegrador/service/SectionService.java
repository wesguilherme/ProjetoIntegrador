package com.projetointegrador.service;

import com.projetointegrador.entity.Section;
import com.projetointegrador.repository.SectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {


    @Autowired
    private SectionPersistence sectionPersistence;

    public SectionService() {
    }

    public SectionService(SectionPersistence sectionPersistence) {
        this.sectionPersistence = sectionPersistence;
    }


    /**
     * @param sectionId - é esperado o parametro cpf do vendedor
     * @return - retorna a verificaçao de duplicidade do código
     * @author Grupo 5 - Tester Wesley
     */
    private boolean codigoNaoUtilizado(Long sectionId) {
        Optional<Section> sectionExistente = sectionPersistence.findById(sectionId);
        if (sectionExistente != null) {
            return true;
        }
        return false;
    }


    public Section cadastrar(Section section) {
        if (codigoNaoUtilizado(section.getSectionId())) {
            return sectionPersistence.save(section);
        } else {
            throw new RuntimeException("Setor já utilizado");
        }

    }

//    public Section cadastrar(Section section, Double usedSpace, Double availableSpace, Double volume) {
//        if (codigoNaoUtilizado(section.getSectionId()) && espacoDisponivel(volume, section)) {
//            var totalUtilizado = section.getUsedSpace()+volume;
//            section.setUsedSpace(totalUtilizado);
//            return sectionPersistence.save(section);
//        } else {
//            throw new RuntimeException("Setor já utilizado");
//        }
//
//    }

    public Optional<Section> buscaSetorPorId(Long sectionId) {
        return sectionPersistence.findById(sectionId);
    }

//    public boolean espacoDisponivel(Long sectionId){
//        if (section.getTotalCapacity() > volume) {
//            return true;
//        } else {
//            return false;
//        }
//        Section section = buscaSetorPorId(sectionId);
//        if (section.get)
//    }

    public boolean verificaSetorValido(Long sectionId) {
        Optional<Section> verificaSetorValido = sectionPersistence.findById(sectionId);
        if (verificaSetorValido.isEmpty()) {
            return false;
        }
        return true;
    }
}
