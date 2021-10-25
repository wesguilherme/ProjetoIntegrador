package com.projetointegrador.controller;

import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.Section;
import com.projetointegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Section> cadastrar(@RequestBody SectionDto sectionDto, UriComponentsBuilder uriBuilder) throws IOException {
        Section sectionCadastrado = sectionService.insert(sectionDto);

        URI uri = uriBuilder.path("/section/buscar/{id}").buildAndExpand(sectionCadastrado.getSectionCode()).toUri();
        return ResponseEntity.created(uri).body(sectionCadastrado);
    }

    @GetMapping("/buscar/{code}")
    public Optional<Section> buscaSetorPorId(@PathVariable("id") String code) {
        return sectionService.buscaSetorPorId(code);
    }

    @GetMapping("/verificar/{code}")
    public boolean verificaSetorValido(@PathVariable("code") String code) {
        return sectionService.verificaSetorValido(code);
    }
}
