package com.projetointegrador.dto;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.BuyerPersistence;
import com.projetointegrador.service.BuyerService;
import com.projetointegrador.service.OrderStatusService;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.PurchaseItemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto {

    private PurchaseOrder purchaseOrder = new PurchaseOrder();

    @NotNull
    private LocalDate date;
    @NotNull
    private Long buyerId;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private List<ProductItemDto> products;

    public PurchaseOrder convert(PurchaseOrderDto purchaseOrderDto, PurchaseItemService purchaseItemService, BuyerService buyerService, ProductService productService, OrderStatusService orderStatusService) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDate(purchaseOrderDto.getDate());
        Buyer buyer = buyerService.getByIdBuyer(purchaseOrderDto.getBuyerId());
        purchaseOrder.setBuyer(buyer);

        OrderStatus orderStatus = orderStatusService.getByOrderStatus(purchaseOrderDto.getOrderStatus().getStatusCode());

        purchaseOrder.setOrderStatus(orderStatus);

        this.purchaseOrder = purchaseOrder;

        List<PurchaseItem> purchaseItem = convertPurchaseItem(purchaseOrderDto.getProducts(), productService);
        purchaseOrder.setPurchaseItems(purchaseItem);

        return purchaseOrder;
    }

    private List<PurchaseItem> convertPurchaseItem(List<ProductItemDto> productItemDto, ProductService productService){
        List<PurchaseItem> purchaseItem = new ArrayList<>();

        for (ProductItemDto item : productItemDto) {
            PurchaseItem pur = new PurchaseItem();

            Product product = productService.getByIdProduct(item.getProductId());
            pur.setProduct(product);
            pur.setQuantity(item.getQuantity());
            pur.setPurchaseOrder(this.purchaseOrder);
            purchaseItem.add(pur);
        }
        return purchaseItem;
    }
}
