package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemCartDto;
import com.projetointegrador.dto.ProductItemListDto;
import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.PurchaseItem;
import com.projetointegrador.entity.PurchaseOrder;
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

    @Autowired
    private BatchStockService batchStockService;

    public PurchaseItemService() {}

    /**
     * @param purchaseItemPersistence é esperado um objeto do tipo purchaseItemPersistence para injeção de depêndencia
     */
    public PurchaseItemService(PurchaseItemPersistence purchaseItemPersistence) {
        this.purchaseItemPersistence = purchaseItemPersistence;
    }

    /**
     * @param purchaseItemPersistence é esperado um objeto do tipo purchaseItemPersistence para injeção de depêndencia
     * @param productService é esperado um objeto do tipo productService para injeção de depêndencia
     * @param batchStockService é esperado um objeto do tipo batchStockService para injeção de depêndencia
     */
    public PurchaseItemService(PurchaseItemPersistence purchaseItemPersistence, ProductService productService, BatchStockService batchStockService) {
        this.purchaseItemPersistence = purchaseItemPersistence;
        this.productService = productService;
        this.batchStockService = batchStockService;
    }

    /**
     * @param products é esperado uma lista de objetos do tipo ProductItemListDto
     * @throws RuntimeException caso não exista um purchaseItem com esse id!
     * @throws RuntimeException caso a lista de produtos esteja vazia!!
     */
    public void update(List<ProductItemListDto> products) {
        if(!products.isEmpty()){
            for (ProductItemListDto item: products) {
                Optional<PurchaseItem> purchaseItem = purchaseItemPersistence.findById(item.getPurchaseItemId());
                if (purchaseItem.isPresent()) {
                    Product product = productService.getByIdProduct(item.getProductId());
                    purchaseItem.get().setProduct(product);
                    purchaseItem.get().setQuantity(item.getQuantity());
                    purchaseItemPersistence.save(purchaseItem.get());
                    atualizaBatchStock(item.getProductId(), purchaseItem.get().getQuantity(), item.getQuantity());
                } else {
                    throw new RuntimeException("Não existe purchaseItem com esse id!");
                }
            }
        }else{
            throw new RuntimeException("A lista de produtos não pode ser vazia!");
        }
    }

    /**
     *
     * @param productId é esperado um parâmetro do tipo productId
     * @param qtdAtual é esperado um parâmetro do tipo qtdAtual
     * @param qtdAlterar é esperado um parâmetro do tipo qtdAlterar
     */
    private void atualizaBatchStock(String productId, int qtdAtual, int qtdAlterar){
        ProductItemCartDto productItemCartDto = batchStockService.getBatchStockByProductId(productId);
        Optional<BatchStock> batchStock = batchStockService.getBatchStockById(productItemCartDto.getBatchStockId());

        if(!batchStock.isEmpty()){
            int soma = 0;
            int qtdStock = 0;
            if(qtdAlterar<qtdAtual){
                for(int i=qtdAlterar;i<qtdAtual;i++){
                    soma += 1;
                }
                qtdStock = batchStock.get().getCurrentQuantity() + soma;
            }else{
                for(int i=qtdAtual;i<qtdAlterar;i++){
                    soma += 1;
                }
                qtdStock = batchStock.get().getCurrentQuantity() - soma;
            }

            batchStock.get().setCurrentQuantity(qtdStock);
        }
    }
}
