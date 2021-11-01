package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.dto.PurchaseOrderDto;
import com.projetointegrador.dto.TotalPrice;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        List<PurchaseItem> purchaseItem = convertPurchaseItem(purchaseOrderDto.getProducts());
        purchaseOrder.setPurchaseItems(purchaseItem);

        OrderStatus orderStatus = orderStatusService.insert(purchaseOrderDto.getOrderStatus());
        purchaseOrder.setOrderStatus(orderStatus);

        return purchaseOrder;
    }

    private List<PurchaseItem> convertPurchaseItem(List<ProductItemDto> productItemDto) {
        List<PurchaseItem> purchaseItem = new ArrayList<>();

        for (ProductItemDto item : productItemDto) {
            PurchaseItem pur = new PurchaseItem();

            Product product = productService.getByIdProduct(item.getProductId());
            pur.setProduct(product);

            pur.setQuantity(item.getQuantity());
            purchaseItem.add(pur);
        }
        return purchaseItem;
    }

    public TotalPrice getTotalprice(List<ProductItemDto> productItemDto) {


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
}
