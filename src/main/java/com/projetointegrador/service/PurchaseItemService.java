package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemListDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.PurchaseItem;
import com.projetointegrador.repository.PurchaseItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseItemService {

    @Autowired
    private PurchaseItemPersistence purchaseItemPersistence;

    @Autowired
    private ProductService productService;

    public PurchaseItemService() {}

    public PurchaseItemService(PurchaseItemPersistence purchaseItemPersistence) {
        this.purchaseItemPersistence = purchaseItemPersistence;
    }

    public PurchaseItemService(PurchaseItemPersistence purchaseItemPersistence, ProductService productService ) {
        this.purchaseItemPersistence = purchaseItemPersistence;
        this.productService = productService;
    }

    public void update(List<ProductItemListDto> products) {
        if(!products.isEmpty()){
            for (ProductItemListDto item: products) {
                Optional<PurchaseItem> purchaseItem = purchaseItemPersistence.findById(item.getPurchaseItemId());
                if (purchaseItem.isPresent()) {
                    Product product = productService.getByIdProduct(item.getProductId());
                    purchaseItem.get().setProduct(product);
                    purchaseItem.get().setQuantity(item.getQuantity());
                    purchaseItemPersistence.save(purchaseItem.get());
                } else {
                    throw new RuntimeException("Não existe purchaseItem com esse id!");
                }
            }
        }else{
            throw new RuntimeException("A lista de produtos não pode ser vazia!");
        }
    }
}
