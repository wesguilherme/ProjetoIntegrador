package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.InboundOrderDto;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.service.BatchStockService;
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
@RequestMapping(value="/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductSellerService productSellerService;

    @Autowired
    private BatchStockService batchStockService;

    @PostMapping(value = "/inboundorder")
    public ResponseEntity<List<BatchStockDto>> insert(@RequestBody @Valid InboundOrderDto inboundOrderDto, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrderCadastrado = inboundOrderService.insert(inboundOrderDto.convert(inboundOrderDto, sectionService, productSellerService));

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

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<?> warehouseListProduct(@PathVariable("id") String id) {
        Product product = inboundOrderService.WarehouseProductList(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/due-date/{sectionCode}/{quantityOfDays}")
    public ResponseEntity<?> batchStockInSection(@PathVariable("sectionCode") String sectionCode, @PathVariable("quantityOfDays") Integer quantityOfDays) {
        List<BatchStockPersistence.BatchStockListByDays> batchStock = batchStockService.batchStockInSection(sectionCode, quantityOfDays);
        return ResponseEntity.ok().body(batchStock);
    }

    @GetMapping(value = "/due-date/list/{quantityOfDays}/{typeId}/{classification}")
    public ResponseEntity<?> batchStockListWithFilter(@PathVariable("quantityOfDays") Integer quantityOfDays, @PathVariable("typeId") Long typeId, @PathVariable("classification") String classification) {
        List<BatchStockPersistence.BatchStockListByFilter> batchStockList = batchStockService.batchStockListWithFilter(quantityOfDays, typeId, classification);
        return ResponseEntity.ok().body(batchStockList);
    }
}
