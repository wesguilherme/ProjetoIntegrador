package com.projetointegrador.service.unit;

import com.projetointegrador.dto.ProductResponseDto;
import com.projetointegrador.dto.ShippingResponseDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ProductSellerPersistence;
import com.projetointegrador.repository.ShippingPersistence;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.ShippingService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShippingServiceTest {


    @Test
    void shouldInsertShipping() {
        ShippingPersistence mock1 = mock(ShippingPersistence.class);
        ShippingService mock = mock(ShippingService.class);
        Warehouse warehouse = Warehouse.builder().warehouseCode("ARM-001").description("Melicidade").build();
        Buyer buyer = Buyer.builder().buyerId(1l).name("Alessandro").cpf("11122233344").build();
        Product product = Product.builder().productId("MLB-410").name("Banana").description("Caixa de Banana").build();
        States states = States.builder().cep("41004145").nome("SP").valorFrete(410.50).build();
        Shipping shipping = Shipping.builder().shippingId("Envio-001").warehouse(warehouse).buyer(buyer).product(product).states(states).build();

        when(mock.insert(any(Shipping.class))).thenReturn(shipping);

        ShippingService shippingService = new ShippingService(mock1);
        shippingService.insert(shipping);
        assertNotNull(shipping.getShippingId());
    }

    @Test
    void shouldListShipping() {
        ShippingPersistence mock1 = mock(ShippingPersistence.class);
        ShippingService mock = mock(ShippingService.class);

        List<ShippingResponseDto> shippingResponseDtoList = new ArrayList<>();
        ShippingResponseDto shippingResponseDto = ShippingResponseDto.builder().shippingId("Envio-001").warehouseCode("ARM-001").buyerId("Alessandro").productId("MLB-123").cep("41004145").nome("SP").valorFrete(410.50).build();
        shippingResponseDtoList.add(shippingResponseDto);

        Warehouse warehouse = Warehouse.builder().warehouseCode("ARM-001").description("Melicidade").build();
        Buyer buyer = Buyer.builder().buyerId(1l).name("Alessandro").cpf("11122233344").build();
        Product product = Product.builder().productId("MLB-410").name("Banana").description("Caixa de Banana").build();
        States states = States.builder().cep("41004145").nome("SP").valorFrete(410.50).build();
        Shipping shipping = Shipping.builder().shippingId("Envio-001").warehouse(warehouse).buyer(buyer).product(product).states(states).build();


        when(mock1.findAll()).thenReturn(Collections.singletonList(shipping));
        when(mock.listShipping()).thenReturn(shippingResponseDtoList);

        ShippingService shippingService = new ShippingService(mock1);

        List<ShippingResponseDto> shippingResponseDtos = shippingService.listShipping();
        assertEquals(1, shippingResponseDtos.size());
    }
}
