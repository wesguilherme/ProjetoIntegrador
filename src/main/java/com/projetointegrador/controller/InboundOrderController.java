package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.InboundOrderDto;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Type;
import com.projetointegrador.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @PostMapping(value = "/inboundorder")
    public ResponseEntity<List<BatchStockDto>> insert(@RequestBody @Valid InboundOrderDto inboundOrderDto, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrderCadastrado = inboundOrderService.insert(inboundOrderDto);

        URI uri = uriBuilder.path("/inboundorder/search/{id}").buildAndExpand(inboundOrderCadastrado.getInboundOrderId()).toUri();
        return ResponseEntity.created(uri).body(BatchStockDto.convertBatchStock(inboundOrderCadastrado.getBatchStock()));
    }

    @GetMapping("/list/{initials}")
    public ResponseEntity<?> productList(@PathVariable("initials") String initials) {
        List<Product> product = inboundOrderService.productList(initials);

        if (product.size()==0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }
}
