package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.entity.Section;
import com.projetointegrador.repository.SectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionPersistence sectionPersistence;

    @Autowired
    private ProductSellerService productSellerService;

    public SectionService() {}

    public SectionService(ProductSellerService productSellerService, SectionPersistence sectionPersistence) {
        this.productSellerService = productSellerService;
        this.sectionPersistence = sectionPersistence;
    }

    /**
     * @param sectionPersistence - é esperado um parâmetro do tipo sectionPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Wesley
     */
    public SectionService(SectionPersistence sectionPersistence) {
        this.sectionPersistence = sectionPersistence;
    }

    public SectionService(ProductSellerService productSellerService) {
        this.productSellerService = productSellerService;
    }

    /**
     * @param section - é esperado um objeto do tipo sectionDto
     * @return - retorna section cadastrado na lista
     * @author - Grupo 5 - Tester Wesley
     */
    public Section insert(Section section) {
        if (section.getRepresentative().getRepresentativeId() != null && section.getWarehouse().getWarehouseCode() != null) {
            return sectionPersistence.save(section);
        }
        throw new RuntimeException("Representante ou armazém não existe!");
    }

    /**
     * @param code - é esperado o parametro code da section
     * @return - retorna a section através do Id
     * @author - Grupo 5 - Tester Wesley
     */
    public Section getSectionByCode(String code) {
        Optional<Section> val;

        val = sectionPersistence.findBySectionCode(code);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe um setor com esse código!");
        }
    }

    public Section getRepresentative(Long id) {
        Optional<Section> val;

        val = sectionPersistence.findByRepresentativeRepresentativeId(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Representative para essa busca!");
        }
    }

    /**
     * @param code - é esperado o parametro code da section
     * @return - retorna a verificação true ou false sobre a validade da section
     * @author - Grupo 5 - Tester Wesley
     */
    public boolean verifyValidSection(String code) {
        Optional<Section> verifyValidSection = sectionPersistence.findBySectionCode(code);
        if (verifyValidSection.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param section
     * @param batchStockDto
     * @return retorna a verificação do espaço na section
     * @author - Grupo 5
     */
    public Boolean verifyAvailableSpace(Section section, List<BatchStockDto> batchStockDto) {
        Double totalVolumeProduct = 0d;
        for (BatchStockDto item : batchStockDto) {
            ProductSeller productSeller = productSellerService.getProductSeller(item.getProductSellerId());
            totalVolumeProduct += productSeller.getVolume();
        }
        if (section.getUsedSpace() < section.getTotalCapacity()) {
            Double availableSpace = section.getTotalCapacity() - section.getUsedSpace();
            if (availableSpace < totalVolumeProduct) {
                throw new RuntimeException("O volume não cabe nesse setor");
            } else {
                Double usedSpace = section.getUsedSpace() + totalVolumeProduct;
                section.setUsedSpace(usedSpace);
                return true;
            }
        } else {
            throw new RuntimeException("Setor sem espaço disponível");
        }
    }

    public boolean verifyEqualType(String environmentType, Long productSellerId) {
        ProductSeller productSeller = productSellerService.getProductSeller(productSellerId);
        if (environmentType.equals(productSeller.getProduct().getType().getEnvironmentType())) {
            return true;
        } else {
            throw new RuntimeException("Verifique se os produtos pertencem ao mesmo tipo do setor ao qual deseja armazenar!");
        }
    }
}