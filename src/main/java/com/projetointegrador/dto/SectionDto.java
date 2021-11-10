package com.projetointegrador.dto;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.entity.Section;
import com.projetointegrador.entity.Type;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.service.RepresentativeService;
import com.projetointegrador.service.TypeService;
import com.projetointegrador.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    @NotNull
    @NotBlank
    private String sectionCode;
    @NotNull
    private Double totalCapacity;
    @NotNull
    private Long typeId;
    @NotNull
    private Long representativeId;
    @NotNull
    @NotBlank
    private String warehouseCode;

    /**
     * @param sectionDto - é esperado um objeto do tipo sectionDto
     * @return - retorna a section com os dados de representative e warehouse
     * @author - Grupo 5 - Tester Wesley
     */
    public Section convert(SectionDto sectionDto, TypeService typeService, RepresentativeService representativeService, WarehouseService warehouseService) {
        Section section = new Section();
        section.setSectionCode(sectionDto.getSectionCode());
        section.setTotalCapacity(sectionDto.getTotalCapacity());
        section.setUsedSpace(0d);

        Type type = typeService.getTypeByTypeId(sectionDto.getTypeId());
        section.setType(type);

        Representative r = representativeService.getByIdRepresentative(sectionDto.getRepresentativeId());
        Warehouse w = warehouseService.getByCode(sectionDto.getWarehouseCode());


        section.setRepresentative(r);
        section.setWarehouse(w);

        return section;
    }
}
