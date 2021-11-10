package com.projetointegrador.dto;

import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.entity.Seller;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.SellerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSellerDto {

    @NotNull
    private Double volume;
    @NotNull
    private Double maximumTemperature;
    @NotNull
    private Double minimumTemperature;
    @NotNull
    private Long sellerId;
    @NotNull
    private String productId;
    @NotNull
    private BigDecimal price;

    public ProductSeller convert(ProductSellerDto productSellerDto, ProductService productService, SellerService sellerService) {
        ProductSeller productSeller = new ProductSeller();
        productSeller.setVolume(productSellerDto.getVolume());
        productSeller.setMaximumTemperature(productSellerDto.getMaximumTemperature());
        productSeller.setMinimumTemperature(productSellerDto.getMinimumTemperature());
        productSeller.setPrice(productSellerDto.getPrice());

        Product p = productService.getByIdProduct(productSellerDto.getProductId());
        Seller s = sellerService.getByIdSeller(productSellerDto.getSellerId());

        productSeller.setProduct(p);
        productSeller.setSeller(s);

        return productSeller;
    }
}
