package com.projetointegrador.controller;

import com.projetointegrador.dto.BlackFridayResponseDto;
import com.projetointegrador.entity.BlackFriday;
import com.projetointegrador.service.BlackFridayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/blackfriday/")
public class BlackFridayController {

    @Autowired
    BlackFridayService blackFridayService;

    @PostMapping(value = "/insert")
    public ResponseEntity<BlackFriday> insert(@RequestBody @Valid BlackFriday blackFriday, UriComponentsBuilder uriBuilder) throws IOException {
        BlackFriday blackFridayCadastrado = blackFridayService.insert(blackFriday);

        URI uri = uriBuilder.path("/blackFriday/search/{productId}").buildAndExpand(blackFridayCadastrado.getProductId()).toUri();
        return ResponseEntity.created(uri).body(blackFridayCadastrado);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<BlackFridayResponseDto>> getBlackFridaysProductList() {
        List<BlackFridayResponseDto> blackFridayResponseDtoList = blackFridayService.listProduct();

        if (blackFridayResponseDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(blackFridayResponseDtoList);
    }

    @PutMapping(value = "/update/{productId}")
    public ResponseEntity<BlackFriday> update(@RequestBody @Valid BlackFriday blackFriday, @PathVariable("productId") String productId, UriComponentsBuilder uriBuilder) {
        BlackFriday blackFridayCadastrado = blackFridayService.update(blackFriday, productId);

        URI uri = uriBuilder.path("/blackFriday/search/{productId}").buildAndExpand(blackFridayCadastrado.getProductId()).toUri();
        return ResponseEntity.created(uri).body(blackFridayCadastrado);
    }

    @DeleteMapping(value = "/delete/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") String productId) throws IOException {
        blackFridayService.delete(productId);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    novo endpoint que compara carrinhos com e sem desconto
}