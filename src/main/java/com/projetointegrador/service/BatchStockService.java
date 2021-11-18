package com.projetointegrador.service;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.BatchStockPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSellerService productSellerService;

    public BatchStockService() {}

    /**
     * @param batchStockPersistence é esperado o parâmetro batchStockPersistence para injeção de depêndencia
     * @author - Grupo 5
     */
    public BatchStockService(BatchStockPersistence batchStockPersistence) {
        this.batchStockPersistence = batchStockPersistence;
    }

    /**
     * @param batchStockPersistence é esperado o parâmetro batchStockPersistence para injeção de depêndencia
     * @param productService é esperado o parâmetro productService para injeção de depêndencia
     * @param productSellerService é esperado o parâmetro productSellerService para injeção de depêndencia
     */
    public BatchStockService(BatchStockPersistence batchStockPersistence, ProductService productService, ProductSellerService productSellerService) {
        this.batchStockPersistence = batchStockPersistence;
        this.productService = productService;
        this.productSellerService = productSellerService;
    }

    /**
     * @param batchStockId é esperado o parâmetro id do batchStock
     * @return o BatchStock existente
     * @author - Grupo 5
     * @throws RuntimeException caso não exista BatchStock
     */
    public Optional<BatchStock> getBatchStockById(Long batchStockId) {

        Optional<BatchStock> val = batchStockPersistence.findById(batchStockId);

        if (val.isPresent()) {
            return val;
        } else {
            throw new RuntimeException("Não existe batchStock para esse produto.");
        }
    }

    /**
     * @param id é esperado o parâmetro id do produto
     * @return lista de batchStockResponseDto por produto
     * @author - Grupo 5
     */
    public BatchStockResponseDto listBatchStockByProductId(String id) {
        BatchStockResponseDto batchStockResponseDto = new BatchStockResponseDto();
        List<BatchStockList> batchStockList = new ArrayList<>();

        Product product = productService.getByIdProduct(id);

        ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

        List<BatchStock> batchStock = batchStockPersistence.findByProductSeller(productSeller);

        for (BatchStock item : batchStock) {
            BatchStockResponseDto bat = new BatchStockResponseDto();
            SectionResponseDto sectionResponseDto = new SectionResponseDto();
            sectionResponseDto.setSectionCode(item.getInboundOrder().getSection().getSectionCode());
            sectionResponseDto.setWarehouseCode(item.getInboundOrder().getSection().getWarehouse().getWarehouseCode());

            batchStockResponseDto.setSectionResponseDto(sectionResponseDto);
            batchStockResponseDto.setProductId(item.getProductSeller().getProduct().getProductId());

            BatchStockList bs = new BatchStockList();
            bs.setBatchStockNumber(item.getBatchStockNumber());
            bs.setCurrentQuantity(item.getCurrentQuantity());
            bs.setDueDate(item.getDueDate());

            batchStockList.add(bs);
        }

        batchStockResponseDto.setBatchStockList(batchStockList);

        return batchStockResponseDto;
    }

    /**
     *
     * @param id é esperado o parâmetro id do produto
     * @param ordination é esperado o parâmetro de ordenação
     * @return lista de batchStockResponseDto por produto ordenada por
     * L - lote
     * C - quantidade atual
     * F - data de validade
     * @author - Grupo 5
     */
    public BatchStockResponseDto listBatchStockWithFilter(String id, String ordination) {
        BatchStockResponseDto batchStockResponseDto = new BatchStockResponseDto();
        List<BatchStockList> batchStockList = new ArrayList<>();

        Product product = productService.getByIdProduct(id);

        ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

        List<BatchStock> batchStock = batchStockPersistence.findByProductSeller(productSeller);

        for (BatchStock item : batchStock) {
            BatchStockResponseDto bat = new BatchStockResponseDto();
            SectionResponseDto sectionResponseDto = new SectionResponseDto();
            sectionResponseDto.setSectionCode(item.getInboundOrder().getSection().getSectionCode());
            sectionResponseDto.setWarehouseCode(item.getInboundOrder().getSection().getWarehouse().getWarehouseCode());

            batchStockResponseDto.setSectionResponseDto(sectionResponseDto);
            batchStockResponseDto.setProductId(item.getProductSeller().getProduct().getProductId());

            BatchStockList bs = new BatchStockList();
            bs.setBatchStockNumber(item.getBatchStockNumber());
            bs.setCurrentQuantity(item.getCurrentQuantity());
            bs.setDueDate(item.getDueDate());

            batchStockList.add(bs);
        }

        if (ordination.equalsIgnoreCase("L")) {
            List<BatchStockList> batchSortedByBatchNumber = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getBatchStockNumber().compareTo(b.getBatchStockNumber()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByBatchNumber);
        } else if (ordination.equalsIgnoreCase("C")) {
            List<BatchStockList> batchSortedByCurrentQuantity = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getCurrentQuantity().compareTo(b.getCurrentQuantity()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByCurrentQuantity);
        } else if (ordination.equalsIgnoreCase("F")) {
            List<BatchStockList> batchSortedByDueDate = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getDueDate().compareTo(b.getDueDate()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByDueDate);
        }

        return batchStockResponseDto;
    }

    /**
     * @param productSeller é esperado o objeto do tipo productSeller
     * @return batchStock para o produto
     * @throws RuntimeException caso não exista batchStock para o produto
     * @author - grupo 5
     */
    public List<BatchStock> getBatchStockByProductSeller(ProductSeller productSeller) {

        List<BatchStock> val = batchStockPersistence.findByProductSeller(productSeller);

        if (!val.isEmpty()) {
            return val;
        } else {
            throw new RuntimeException("Não existe batchStock para esse produto.");
        }
    }

    /**
     * @param productItemDto é esperado o parâmetro de uma lista do objeto productItemDto
     * @throws RuntimeException caso a validade do produto seja inferior a 3 semanas
     * @throws RuntimeException caso não exista estoque para o produto
     * @author - Grupo 5
     */
    public void verifyProductInBatchStock(List<ProductItemDto> productItemDto) {

        for (ProductItemDto item : productItemDto) {

            Product product = productService.getByIdProduct(item.getProductId());
            ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

            List<BatchStock> batchStock = getBatchStockByProductSeller(productSeller);

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = batchStock.get(0).getDueDate();

            int period = Period.between(startDate, endDate).getDays();

            if (item.getQuantity() <= batchStock.get(0).getCurrentQuantity()) {
                if (period >= 21) {
                    String resp = "Validade do produto: " + item.getProductId() + " é inferior a 3 semanas";
                    throw new RuntimeException(resp);
                }
            } else {
                String resp = "Não existe estoque para este produto: " + item.getProductId();
                throw new RuntimeException(resp);
            }
        }
    }

    /**
     * @param sectionCode é esperado o parâmetro de codigo da section
     * @param quantityOfDays é esperado o parâmetro de quantidade do batchStock
     * @return lista de batchStock ordenada pela data de validade
     * @author - Grupo 5
     */
    public List<BatchStockPersistence.BatchStockListByDays> batchStockInSection(String sectionCode, Integer quantityOfDays) {
        List<BatchStockPersistence.BatchStockListByDays> batchStocks = batchStockPersistence.listbatchByDays(sectionCode, quantityOfDays);
        return batchStocks;
    }

    /**
     *
     * @param quantityOfDays é esperado o parâmetro de quantidade de dias do batchStock
     * @param typeId é esperado o parâmetro de id do tipo de produto
     * @param pageable é esperado o objeto do tipo pageable
     * @return lista de batchStockList ordenada pela data de validade ASC ou DESC
     * @author - Grupo 5
     */
    public List<BatchStockPersistence.BatchStockListByFilter> batchStockListWithFilter(Integer quantityOfDays, Long typeId, Pageable pageable) {
        List<BatchStockPersistence.BatchStockListByFilter> batchStocksFilter = batchStockPersistence.listbatchByFilter(quantityOfDays, typeId, pageable);
        return batchStocksFilter;
    }

    /**
     * @param productId é esperado o parâmetro id do produto
     * @return productItemCartDto em batchStock
     * @author - Grupo 5
     */
    public ProductItemCartDto getBatchStockByProductId(String productId){
        BatchStockPersistence.BatchStockByProductId batchStockProductId = batchStockPersistence.batchStockByProductId(productId);
        ProductItemCartDto productItemCartDto = new ProductItemCartDto();
        productItemCartDto.setBatchStockId(batchStockProductId.getBatch_stock_id());
        productItemCartDto.setProductId(batchStockProductId.getProduct_id());
        productItemCartDto.setQuantity(batchStockProductId.getCurrent_quantity());
        return productItemCartDto;
    }
}
