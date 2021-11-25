package com.projetointegrador.dto;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.service.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class ShippingDto {

    @NotNull
    @NotBlank
    private String shippingId;
    private String warehouseCode;
    private Long buyerId;
    private String productId;
    private String name;
    private String description;
    private String cep;



//    private File arquivo;
//
//    public ShippingDto(File arquivo){
//        this.arquivo = arquivo;
//    }

    public Shipping convert(ShippingDto shippingDto, WarehouseService warehouseService, BuyerService buyerService, ProductService productService, StatesService statesService) {
        Shipping shipping = new Shipping();
        shipping.setShippingId(shippingDto.getShippingId());

        Warehouse warehouse = warehouseService.getByCode(shippingDto.getWarehouseCode());
        shipping.setWarehouse(warehouse);

        Buyer buyer = buyerService.getByIdBuyer(shippingDto.getBuyerId());
        shipping.setBuyer(buyer);

        Product product = productService.getByIdProduct(shippingDto.getProductId());
        shipping.setProduct(product);

        Product name = productService.getByIdProduct(shippingDto.getName());
        shipping.setProduct(name);

        Product description = productService.getByIdProduct(shippingDto.getDescription());
        shipping.setProduct(description);

        States states = statesService.getStates(shippingDto.getCep());
        shipping.setStates(states);


        return shipping;
    }

//    public void cadastro(List<Shipping> shippings) throws IOException {
//        try(FileOutputStream fos = new FileOutputStream(shippingId);
//            OutputStreamWriter osw = new OutputStreamWriter(fos);
//            BufferedWriter bw = new BufferedWriter(osw)
//        ){
//            bw.write("");
//        }
//
//        try(
//                FileOutputStream fos = new FileOutputStream(shippingId);
//                OutputStreamWriter osw = new OutputStreamWriter(fos);
//                BufferedWriter bw = new BufferedWriter(osw)
//        ){
//            for (ShippingDto shippingDto : shippings) {
//                String registro = shippingDto.getShippingId() + ";" + shippingDto.getProductId() + ";" + shippingDto.getWarehouseCode() + ";" + shippingDto.getBuyerId();
//                bw.append(registro);
//                bw.newLine();
//            }
//        }
//    }
}
