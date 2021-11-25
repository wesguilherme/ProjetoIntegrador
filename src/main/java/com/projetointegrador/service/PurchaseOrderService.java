package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemCartDto;
import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.dto.PurchaseOrderResponseDto;
import com.projetointegrador.dto.TotalPrice;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Autowired
    private BlackFridayService blackFridayService;

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

    /**
     *
     * @param purchaseOrder
     * @return  retorna a verificaçao da quantidade do produto no estoque
     * @author - Grupo 5
     */
    public PurchaseOrder insert(PurchaseOrder purchaseOrder) {
        atualizaBatchStock(purchaseOrder);
        return purchaseOrderPersistence.save(purchaseOrder);
    }

    private void atualizaBatchStock(PurchaseOrder purchaseOrder){
        for (PurchaseItem item: purchaseOrder.getPurchaseItems()) {
            ProductItemCartDto productItemCartDto = batchStockService.getBatchStockByProductId(item.getProduct().getProductId());
            Optional<BatchStock> batchStock = batchStockService.getBatchStockById(productItemCartDto.getBatchStockId());

            if (!batchStock.isEmpty()) {
                int qtdStock = batchStock.get().getCurrentQuantity() - item.getQuantity();
                batchStock.get().setCurrentQuantity(qtdStock);
            }
        }
    }

    public BigDecimal blackFridayDiscounts(String productId, BigDecimal price) {
        BlackFriday blackFriday = blackFridayService.getByIdProduct(productId);
        if (blackFriday != null) {
            BigDecimal valor = price.multiply(blackFriday.getDiscount());
            return valor;
        }
        return null;
    }

    private boolean verifyDate(String productId) {
        BlackFriday blackFriday = blackFridayService.getByIdProduct(productId);
        LocalDate dataHoje = LocalDate.now();
        if (blackFriday.getInitialDate().compareTo(dataHoje) >= 0 || dataHoje.compareTo(blackFriday.getFinalDate()) <= 0) {
            return true;
        }
        return false;
    }

    public TotalPrice getTotalprice(List<ProductItemDto> productItemDto) {
        BigDecimal valorTotal = new BigDecimal(0);

        for (ProductItemDto item : productItemDto) {

            Product product = productService.getByIdProduct(item.getProductId());
            ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

            BigDecimal newQtd = new BigDecimal(item.getQuantity());
            BigDecimal newQtd2 = new BigDecimal(0);

            if (blackFridayDiscounts(item.getProductId(), productSeller.getPrice()) != null && verifyDate(item.getProductId()) == true) {
                BigDecimal valWDiscount = blackFridayDiscounts(item.getProductId(), productSeller.getPrice());
                newQtd2 = newQtd.multiply(valWDiscount);
            } else {
                newQtd2 = newQtd.multiply(productSeller.getPrice());
            }

            valorTotal = valorTotal.add(newQtd2);
        }

        TotalPrice totalPrice = new TotalPrice();
        totalPrice.setTotalprice(valorTotal);

        return totalPrice;
    }

    /**
     *
     * @param id
     * @return  retorna a verificaçao do produto através do id
     * @author - Grupo 5
     */
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
