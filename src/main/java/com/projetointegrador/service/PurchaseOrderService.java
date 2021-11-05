package com.projetointegrador.service;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.*;
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
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private ProductSellerService productSellerService;

    public PurchaseOrderService() {
    }



    public PurchaseOrderService(PurchaseOrderPersistence purchaseOrderPersistence) {
        this.purchaseOrderPersistence = purchaseOrderPersistence;
    }

    public PurchaseOrder insert(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = convert(purchaseOrderDto);
        return purchaseOrderPersistence.save(purchaseOrder);
    }

    private PurchaseOrder convert(PurchaseOrderDto purchaseOrderDto) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDate(purchaseOrderDto.getDate());
        Buyer buyer = buyerService.getByIdBuyer(purchaseOrderDto.getBuyerId());
        purchaseOrder.setBayer(buyer);

        OrderStatus orderStatus = orderStatusService.getByOrderStatus(purchaseOrderDto.getOrderStatus().getStatusCode());

        purchaseOrder.setOrderStatus(orderStatus);

        List<PurchaseItem> purchaseItem = convertPurchaseItem(purchaseOrderDto.getProducts(), purchaseOrder);
        purchaseOrder.setPurchaseItems(purchaseItem);

        return purchaseOrder;
    }

    private List<PurchaseItem> convertPurchaseItem(List<ProductItemDto> productItemDto, PurchaseOrder purchaseOrder){
        List<PurchaseItem> purchaseItem = new ArrayList<>();

        for (ProductItemDto item : productItemDto) {
            PurchaseItem pur = new PurchaseItem();

            Product product = productService.getByIdProduct(item.getProductId());
            pur.setProduct(product);
            pur.setQuantity(item.getQuantity());
            pur.setPurchaseOrder(purchaseOrder);
            purchaseItem.add(pur);
        }
        return purchaseItem;
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
