package com.projetointegrador.service;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.ProductPersistence;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderPersistence purchaseOrderPersistence;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSellerService productSellerService;

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    public PurchaseOrderService() {
    }

    public PurchaseOrderService(PurchaseOrderPersistence purchaseOrderPersistence, BatchStockService batchStockService, BatchStockPersistence batchStockPersistence) {
        this.purchaseOrderPersistence = purchaseOrderPersistence;
        this.batchStockService = batchStockService;
        this.batchStockPersistence = batchStockPersistence;
    }

    public PurchaseOrderService(PurchaseOrderPersistence purchaseOrderPersistence) {
        this.purchaseOrderPersistence = purchaseOrderPersistence;
    }

    public PurchaseOrderService(PurchaseOrderPersistence purchaseOrderPersistence, ProductService productService, ProductSellerService productSellerService) {
        this.purchaseOrderPersistence = purchaseOrderPersistence;
        this.productService = productService;
        this.productSellerService = productSellerService;
    }

    public PurchaseOrder insert(PurchaseOrder purchaseOrder) {
        atualizaBatchStock(purchaseOrder);
        return purchaseOrderPersistence.save(purchaseOrder);
    }

    private void atualizaBatchStock(PurchaseOrder purchaseOrder){
        for (PurchaseItem item: purchaseOrder.getPurchaseItems()) {
            ProductItemCartDto productItemCartDto = batchStockService.getBatchStockByProductId(item.getProduct().getProductId());
            Optional<BatchStock> batchStock = batchStockService.getBatchStockById(productItemCartDto.getBatchStockId());

            if(!batchStock.isEmpty()){
                int qtdStock = batchStock.get().getCurrentQuantity() - item.getQuantity();
                batchStock.get().setCurrentQuantity(qtdStock);
            }
        }
    }

    public TotalPrice getTotalprice(List<ProductItemDto> productItemDto){
        BigDecimal valorTotal = new BigDecimal(0);

        for (ProductItemDto item : productItemDto) {

            Product product = productService.getByIdProduct(item.getProductId());
            ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

            productSeller.getPrice();

            BigDecimal newQtd = new BigDecimal(item.getQuantity());
            BigDecimal newQtd2 = newQtd.multiply(productSeller.getPrice());

            valorTotal = valorTotal.add(newQtd2);
        }

        TotalPrice totalPrice = new TotalPrice();
        totalPrice.setTotalprice(valorTotal);

        return totalPrice;
    }

    public PurchaseOrderResponseDto listOrdersByOrderId(Long id) {

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderPersistence.findById(id);
        PurchaseOrderResponseDto purchaseOrderResponseDto = new PurchaseOrderResponseDto();

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatusId(purchaseOrder.get().getOrderStatus().getOrderStatusId());
        orderStatus.setStatusCode(purchaseOrder.get().getOrderStatus().getStatusCode());

        purchaseOrderResponseDto.setOrderStatus(orderStatus);

        purchaseOrderResponseDto.setBuyerId(purchaseOrder.get().getPurchaseOrderId());
        purchaseOrderResponseDto.setDate(purchaseOrder.get().getDate());

        List<ProductItemDto> productItemDtoList = new ArrayList<>();

        for (PurchaseItem item: purchaseOrder.get().getPurchaseItems()) {
            ProductItemDto productItemDto = new ProductItemDto();
            productItemDto.setProductId(item.getProduct().getProductId());
            productItemDto.setQuantity(item.getQuantity());
            productItemDtoList.add(productItemDto);
        }

        purchaseOrderResponseDto.setProducts(productItemDtoList);

        return purchaseOrderResponseDto;
    }
}
