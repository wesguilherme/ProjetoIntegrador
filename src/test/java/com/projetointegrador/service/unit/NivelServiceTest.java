package com.projetointegrador.service.unit;

import com.projetointegrador.dto.LevelBuyerDto;
import com.projetointegrador.entity.Nivel;
import com.projetointegrador.repository.NivelPersistence;
import com.projetointegrador.service.NivelService;
import com.projetointegrador.service.PurchaseOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NivelServiceTest {

    @Test
    void shouldInsertBuyer () {
        NivelPersistence nivelPersistenceMock = mock(NivelPersistence.class);
        Nivel nivel = Nivel.builder().id(1L).beneficios("Desconto").percentualDesconto(30L).valorTotalDeCompra(new BigDecimal(200)).build();
        when(nivelPersistenceMock.save(any(Nivel.class))).thenReturn(nivel);
        NivelService nivelService = new NivelService(nivelPersistenceMock);
        Nivel nivel1 = nivelService.insert(nivel);
        assertNotNull(nivel1.getId());
    }

    @Test
    void shouldUpdateBuyer () {
        NivelPersistence nivelPersistenceMock = mock(NivelPersistence.class);
        Nivel nivel = Nivel.builder().id(1L).beneficios("Desconto").percentualDesconto(30L).valorTotalDeCompra(new BigDecimal(200)).build();
        when(nivelPersistenceMock.save(any(Nivel.class))).thenReturn(nivel);
        when(nivelPersistenceMock.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(nivel));
        NivelService nivelService = new NivelService(nivelPersistenceMock);
        Nivel nivel1 = nivelService.update(nivel,1L);
        assertNotNull(nivel1.getId());
    }

    @Test
    void shouldNotUpdateBuyer () {
        NivelPersistence nivelPersistenceMock = mock(NivelPersistence.class);
        Nivel nivel = Nivel.builder().id(1L).beneficios("Desconto").percentualDesconto(30L).valorTotalDeCompra(new BigDecimal(200)).build();
        when(nivelPersistenceMock.findById(anyLong())).thenReturn(Optional.empty());
        NivelService nivelService = new NivelService(nivelPersistenceMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            nivelService.update(nivel,1L);
        });
        String expectedMessage = "Não existe nivel cadastrado com o id passado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldGetAllNivel () {
        NivelPersistence nivelPersistenceMock = mock(NivelPersistence.class);
        Nivel nivel = Nivel.builder().id(1L).beneficios("Desconto").percentualDesconto(30L).valorTotalDeCompra(new BigDecimal(200)).build();

        List<Nivel> nivelList = new ArrayList<>();
        nivelList.add(nivel);

        when(nivelPersistenceMock.findAll()).thenReturn(nivelList);

        NivelService nivelService = new NivelService(nivelPersistenceMock);
        List<Nivel> nivel1 = nivelService.getAllNivel();
        assertEquals(1, nivel1.size());
    }

    @Test
    void shouldCustomerLevelHistory(){
        NivelPersistence nivelPersistenceMock = mock(NivelPersistence.class);
        PurchaseOrderService purchaseOrderServiceMock = mock(PurchaseOrderService.class);
        NivelService nivelServiceMock = mock(NivelService.class);

        Nivel nivel = Nivel.builder().id(1L).beneficios("Desconto").percentualDesconto(10L).valorTotalDeCompra(new BigDecimal(100)).build();
        Nivel nivel2 = Nivel.builder().id(2L).beneficios("Desconto e Frete Grátis").percentualDesconto(20L).valorTotalDeCompra(new BigDecimal(200)).build();

        List<Nivel> nivels = new ArrayList<>();
        nivels.add(nivel);
        nivels.add(nivel2);

        when(nivelPersistenceMock.findAll()).thenReturn(nivels);

        List<Nivel> nivelList = new ArrayList<>();
        nivelList.add(nivel);

        LevelBuyerDto levelBuyerDto = LevelBuyerDto.builder().buyerId(1L).nivelAtual(nivel).proximoNivel(nivel2).totalCompras(new BigDecimal(150)).build();

        when(purchaseOrderServiceMock.getTotalPricePurchaseByBuyer(anyLong())).thenReturn(new BigDecimal(150));

        when(nivelServiceMock.customerLevelHistory(anyLong())).thenReturn(levelBuyerDto);

        NivelService nivelService = new NivelService(nivelPersistenceMock,purchaseOrderServiceMock);
        LevelBuyerDto levelBuyerDto1 = nivelService.customerLevelHistory(1L);
        assertNotNull(levelBuyerDto1.getBuyerId());

    }

}