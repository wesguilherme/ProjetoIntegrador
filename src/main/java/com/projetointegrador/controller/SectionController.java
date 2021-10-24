package com.projetointegrador.controller;

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
    public ResponseEntity<Section> cadastrar(@RequestBody Section section, UriComponentsBuilder uriBuilder) throws IOException {
        Section sectionCadastrado = sectionService.cadastrar(section);

        URI uri = uriBuilder.path("/section/buscar/{id}").buildAndExpand(sectionCadastrado.getSectionId()).toUri();
        return ResponseEntity.created(uri).body(sectionCadastrado);
    }

    @GetMapping("/buscar/{id}")
    public Optional<Section> buscaSetorPorId(@PathVariable("id") Long id) {
        return sectionService.buscaSetorPorId(id);
    }

    @GetMapping("/verificar/{id}")
    public boolean verificaSetorValido(@PathVariable("id") Long id) {
        return sectionService.verificaSetorValido(id);
    }
}
