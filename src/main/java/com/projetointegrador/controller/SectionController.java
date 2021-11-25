package com.projetointegrador.controller;

import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.Section;
import com.projetointegrador.service.RepresentativeService;
import com.projetointegrador.service.SectionService;
import com.projetointegrador.service.TypeService;
import com.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private TypeService typeService;

    /**
     * @param sectionDto é esperado um objeto do tipo sectionDto
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return o section cadastrado
     * @throws IOException lança a excessão caso ocorra um erro de cadastro
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Section> insert(@RequestBody @Valid SectionDto sectionDto, UriComponentsBuilder uriBuilder) throws IOException {
        Section sectionCadastrado = sectionService.insert(sectionDto.convert(sectionDto,typeService,representativeService,warehouseService));

        URI uri = uriBuilder.path("/section/search/{id}").buildAndExpand(sectionCadastrado.getSectionCode()).toUri();
        return ResponseEntity.created(uri).body(sectionCadastrado);
    }
}
