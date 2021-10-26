package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/api/v1/")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @PostMapping(value = "fresh-products/inboundorder/")
    public ResponseEntity<BatchStockDto> insert(@RequestBody InboundOrder inboundOrder, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrderCadastrado = inboundOrderService.insert(inboundOrder);

        URI uri = uriBuilder.path("/inboundorder/search/{id}").buildAndExpand(inboundOrderCadastrado.getInboundOrderId()).toUri();
        return ResponseEntity.created(uri).body(BatchStockDto.converter(inboundOrderCadastrado.getBatchStock()));
    }
}