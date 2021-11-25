package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.InboundOrderDto;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.service.InboundOrderService;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductSellerService productSellerService;

    /**
     * @param inboundOrderDto é esperado um objeto do tipo inboundOrderDto
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return inboundOrder cadastrado no banco
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<List<BatchStockDto>> insert(@RequestBody @Valid InboundOrderDto inboundOrderDto, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrderCadastrado = inboundOrderService.insert(inboundOrderDto.convert(inboundOrderDto, sectionService, productSellerService));

        URI uri = uriBuilder.path("/inboundorder/search/{id}").buildAndExpand(inboundOrderCadastrado.getInboundOrderId()).toUri();
        return ResponseEntity.created(uri).body(BatchStockDto.convertBatchStock(inboundOrderCadastrado.getBatchStock()));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<List<BatchStockDto>> update(@RequestBody @Valid InboundOrderDto inboundOrderDto, @PathVariable("id") Long id, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrderCadastrado = inboundOrderService.update(inboundOrderDto.convert(inboundOrderDto, sectionService, productSellerService), id);

        URI uri = uriBuilder.path("/inboundorder/search/{id}").buildAndExpand(inboundOrderCadastrado.getInboundOrderId()).toUri();
        return ResponseEntity.created(uri).body(BatchStockDto.convertBatchStock(inboundOrderCadastrado.getBatchStock()));
    }

    /**
     * @param initials é esperado um parâmetro do tipo initials
     * @return product cadastrado no banco
     */
    @GetMapping("/list/{initials}")
    public ResponseEntity<List<Product>> productList(@PathVariable("initials") String initials) {
        List<Product> product = inboundOrderService.productList(initials);

        if (product.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }
}
