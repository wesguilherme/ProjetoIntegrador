package com.projetointegrador.service;

import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.Representative;
import com.projetointegrador.entity.Section;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.SectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionPersistence sectionPersistence;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private WarehouseService warehouseService;

    public SectionService() {

    }

    public SectionService(SectionPersistence sectionPersistence) {
        this.sectionPersistence = sectionPersistence;
    }

    public Section insert(SectionDto sectionDto) {
        Section section = converte(sectionDto);

        if(section.getRepresentative() != null && section.getWarehouse() != null){
            return sectionPersistence.save(section);
        }

        throw new RuntimeException("Representante ou armazém não existe!");

    }

    public Optional<Section> buscaSetorPorId(String code) {
        return sectionPersistence.findBySectionCode(code);
    }

    public boolean verificaSetorValido(String code) {
        Optional<Section> verificaSetorValido = sectionPersistence.findBySectionCode(code);
        if (verificaSetorValido.isEmpty()) {
            return false;
        }
        return true;
    }

    public Section converte(SectionDto sectionDto){
        Section section = new Section();
        section.setSectionCode(sectionDto.getSectionCode());
        section.setSectionType(sectionDto.getSectionType());
        section.setTotalCapacity(sectionDto.getTotalCapacity());
        section.setUsedSpace(sectionDto.getUsedSpace());

        Representative r = representativeService.getByIdRepresentative(sectionDto.getRepresentativeId());
        Warehouse w = warehouseService.getByCode(sectionDto.getWarehouseCode());

        section.setRepresentative(r);
        section.setWarehouse(w);

        return section;
    }
}
