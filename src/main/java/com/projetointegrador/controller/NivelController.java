package com.projetointegrador.controller;

import com.projetointegrador.dto.LevelBuyerResponseDto;
import com.projetointegrador.entity.Nivel;
import com.projetointegrador.service.BuyerService;
import com.projetointegrador.service.NivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/nivel")
public class NivelController {

    @Autowired
    private NivelService nivelService;

    @Autowired
    private BuyerService buyerService;

    /**
     * @param nivel é esperado objeto do tipo nivel
     * @param uriBuilder é esperado um objeto do tipo UriBuilder
     * @return o nivel cadastrado no banco
     * @throws IOException
     * @author - Ana Carolina
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Nivel> insert(@RequestBody @Valid Nivel nivel, UriComponentsBuilder uriBuilder) throws IOException {
        Nivel nivelCadastrado = nivelService.insert(nivel);

        URI uri = uriBuilder.path("/nivel/{id}").buildAndExpand(nivelCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(nivelCadastrado);
    }

    /**
     *
     * @param nivel é esperado um objeto do tipo nivel
     * @param id é esperado o id do nivel que vai ser editado
     * @param uriBuilder tem que ser passada uma classe uriBuilder
     * @return o nivel editado no banco
     * @author - Ana Carolina
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Nivel> update(@RequestBody @Valid Nivel nivel, @PathVariable("id") Long id, UriComponentsBuilder uriBuilder) throws IOException{
        Nivel nivelCadastrado = nivelService.update(nivel,id);

        URI uri = uriBuilder.path("/nivel/{id}").buildAndExpand(nivelCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(nivelCadastrado);
    }

    /**
     * @return uma lista de niveis cadastrados
     * @author - Ana Carolina
     */
    @GetMapping("/list/")
    public ResponseEntity<List<Nivel>> nivelList() {
        List<Nivel> nivelList = nivelService.getAllNivel();

        if (nivelList.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nivelList);
    }

    /**
     * @return uma lista de niveis cadastrados
     * @author - Ana Carolina
     */
    @GetMapping("/list/nivelBuyer/{id}")
    public ResponseEntity<LevelBuyerResponseDto> nivelBuyer(@PathVariable("id") Long id) {
        LevelBuyerResponseDto levelBuyerResponseDto = LevelBuyerResponseDto.convert(nivelService.customerLevelHistory(id),buyerService);

        if (levelBuyerResponseDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(levelBuyerResponseDto);
    }
}
