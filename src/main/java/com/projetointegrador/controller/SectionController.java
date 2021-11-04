package com.projetointegrador.controller;

import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.Section;
import com.projetointegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping(value = "/insert")
    public ResponseEntity<Section> insert(@RequestBody @Valid SectionDto sectionDto, UriComponentsBuilder uriBuilder) throws IOException {
        Section sectionCadastrado = sectionService.insert(sectionDto);

        URI uri = uriBuilder.path("/section/search/{id}").buildAndExpand(sectionCadastrado.getSectionCode()).toUri();
        return ResponseEntity.created(uri).body(sectionCadastrado);
    }

    @GetMapping("/buscar/{code}")
    public Section getSectionById(@PathVariable("id") String code) {
        return sectionService.getSectionByCode(code);
    }

    @GetMapping("/verificar/{code}")
    public boolean verifyValidSection(@PathVariable("code") String code) {
        return sectionService.verifyValidSection(code);
    }
}
