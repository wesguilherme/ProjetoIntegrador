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

    @Autowired
    private BuyerService buyerService;

    public PurchaseOrderService() {
    }

    public PurchaseOrderService(PurchaseOrderPersistence purchaseOrderPersistence, BuyerService buyerService) {
        this.purchaseOrderPersistence = purchaseOrderPersistence;
        this.buyerService = buyerService;
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

        purchaseOrderResponseDto.setBuyerId(purchaseOrder.get().getBuyer().getBuyerId());
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

    public List<PurchaseOrderResponseDto> getPurchaseByBuyer(Long buyerId){
        Buyer buyer = buyerService.getByIdBuyer(buyerId);
        List<PurchaseOrder> purchaseOrder = purchaseOrderPersistence.findPurchaseOrderByBuyer(buyer);

        List<PurchaseOrderResponseDto> purchaseOrderResponseDtoList = new ArrayList<>();

        for (PurchaseOrder item: purchaseOrder) {
            PurchaseOrderResponseDto purchaseOrderResponseDto = new PurchaseOrderResponseDto();

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrderStatusId(item.getOrderStatus().getOrderStatusId());
            orderStatus.setStatusCode(item.getOrderStatus().getStatusCode());

            purchaseOrderResponseDto.setOrderStatus(orderStatus);

            purchaseOrderResponseDto.setBuyerId(item.getBuyer().getBuyerId());
            purchaseOrderResponseDto.setDate(item.getDate());

            List<ProductItemDto> productItemDtoList = new ArrayList<>();

            for (PurchaseItem item2: item.getPurchaseItems()) {
                ProductItemDto productItemDto = new ProductItemDto();
                productItemDto.setProductId(item2.getProduct().getProductId());
                productItemDto.setQuantity(item2.getQuantity());
                productItemDtoList.add(productItemDto);
            }

            purchaseOrderResponseDto.setProducts(productItemDtoList);

            purchaseOrderResponseDtoList.add(purchaseOrderResponseDto);
        }

        return purchaseOrderResponseDtoList;
    }

    /**
     *
     * @param buyerId é esperado
     * @return
     */
    public BigDecimal getTotalPricePurchaseByBuyer(Long buyerId){
        Buyer buyer = buyerService.getByIdBuyer(buyerId);
        List<PurchaseOrder> purchaseOrder = purchaseOrderPersistence.findPurchaseOrderByBuyer(buyer);

        BigDecimal valorTotal = new BigDecimal(0);
        for (PurchaseOrder itemPO : purchaseOrder) {

            for (PurchaseItem item : itemPO.getPurchaseItems()) {
                ProductSeller productSeller = productSellerService.getProductSellerByProduto(item.getProduct());

                BigDecimal newQtd = new BigDecimal(item.getQuantity());
                BigDecimal newQtd2 = newQtd.multiply(productSeller.getPrice());

                valorTotal = valorTotal.add(newQtd2);
            }
        }
        return valorTotal;
    }
}
