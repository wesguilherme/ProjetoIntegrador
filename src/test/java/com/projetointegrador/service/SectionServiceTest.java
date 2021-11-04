package com.projetointegrador.service;

import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SectionServiceTest {

    @Test
    void shouldInsert(){
        SectionService sectionServiceMock = mock(SectionService.class);
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);
        RepresentativeService representativeServiceMock = mock(RepresentativeService.class);
        WarehouseService warehouseServiceMock = mock(WarehouseService.class);
        TypeService typeServiceMock = mock(TypeService.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Section section = new Section("SEC-123", 200.0, 30.0, type, representative, warehouse);
        SectionDto sectionDto = new SectionDto("SEC-123",200.0,1L,2L,"MLB-410");

        Mockito.when(representativeServiceMock.getByIdRepresentative(2L)).thenReturn(representative);
        Mockito.when(warehouseServiceMock.getByCode("MLB-410")).thenReturn(warehouse);

        when(sectionServiceMock.insert(any(SectionDto.class))).thenReturn(section);

        SectionService sectionService = new SectionService(representativeServiceMock, warehouseServiceMock, typeServiceMock, sectionPersistenceMock);
        sectionService.insert(sectionDto);
        assertNotNull(section.getSectionCode());

    }

    @Test
    void shouldnotInsert() throws RuntimeException{
        RepresentativeService representativeServiceMock = mock(RepresentativeService.class);
        WarehouseService warehouseServiceMock = mock(WarehouseService.class);
        TypeService typeServiceMock = mock(TypeService.class);

        Mockito.when(representativeServiceMock.getByIdRepresentative(Mockito.anyLong())).thenReturn(new Representative());
        Mockito.when(warehouseServiceMock.getByCode(Mockito.anyString())).thenReturn(new Warehouse());

        SectionService sectionService = new SectionService(representativeServiceMock, warehouseServiceMock, typeServiceMock);

        SectionDto sectionDto = new SectionDto("SEC-123",200.0,1L,2L,"MLB-410");
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.insert(sectionDto);
        });

        assertEquals("Representante ou armazém não existe!", exception.getMessage());
    }


    @Test
    void shouldGetSectionByCode() {
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Section> section = Optional.of(new Section("SEC-123", 200.0, 30.0, type, representative, warehouse));

        when(mock.findBySectionCode("SEC-123")).thenReturn(section);

        SectionService sectionService = new SectionService(mock);
        Section section1 = sectionService.getSectionByCode("SEC-123");
        assertNotNull(section1.getSectionCode());

    }


    @Test
    void shouldnotGetSectionByCode() throws RuntimeException{
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Section> section = Optional.of(new Section("SEC-123", 200.0, 30.0, type, representative, warehouse));

        when(mock.findBySectionCode("SEC-123")).thenReturn(section);

        SectionService sectionService = new SectionService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.getSectionByCode(String.valueOf(section));
        });

        String expectedMessage = "Não existe um setor com esse código!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldGetRepresentative(){
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Section> section = Optional.of(new Section("SEC-123", 200.0, 30.0, type, representative, warehouse));

        when(mock.findByRepresentativeRepresentativeId(1L)).thenReturn(section);

        SectionService sectionService = new SectionService(mock);
        Section section1 = sectionService.getRepresentative(1L);
        assertNotNull(section1.getRepresentative());
    }

    @Test
    void shouldnotGetRepresentative()throws RuntimeException{
        SectionPersistence mock = mock(SectionPersistence.class);

        SectionService sectionService = new SectionService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.getRepresentative(1L);
        });

        String expectedMessage = "Não existe Representative para essa busca!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void shouldVerifyValidSection() throws NullPointerException {
//        SectionPersistence mock = mock(SectionPersistence.class);
//        SectionService sectionService = new SectionService(mock);
//
//        assertEquals(false,sectionService.verifyValidSection(String.valueOf(false)));
//
//    }
//
//    @Test
//    void mustverifyEqualType() throws NullPointerException{
//        ProductSellerService mock = mock(ProductSellerService.class);
//        ProductSellerPersistence mock1 =mock(ProductSellerPersistence.class);
//
//        Type type = new Type(1L, "RF", "REFRIGERADOS");
//        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
//        Seller seller = new Seller(1L,"111.222.333-44","Rafael",address);
//        Product product = new Product("MLB-123", "Uva", "Caixa de Uva",type);
//        ProductSeller productSeller = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));
//
////        when(mock.getProductSeller(1L).getProduct().getType().getEnvironmentType()).thenReturn(String.valueOf(productSeller));
//        when(productSeller.getProduct().getType().getEnvironmentType()).thenReturn(String.valueOf(productSeller));
//
//        ProductSeller productSeller1 = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));
//        assertEquals(productSeller.getProduct().getType().getEnvironmentType(), productSeller1.getProduct().getType().getEnvironmentType());
//    }

//    @Test
//    void mustnotverifyEqualType() throws NullPointerException{
//        ProductSellerPersistence mock1 = mock(ProductSellerPersistence.class);
//        ProductSellerService mock = mock(ProductSellerService.class);
//
//        Type type = new Type(1L, "RF", "REFRIGERADOS");
//        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
//        Seller seller = new Seller(1L,"111.222.333-44","Rafael",address);
//        Product product = new Product("MLB-123", "Uva", "Caixa de Uva",type);
//
//        ProductSeller productSeller = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));
//
//        when(mock1.getById(1L)).thenReturn(productSeller);
//        when(productSeller.getProduct().getType().getEnvironmentType().equals(productSeller.getProduct().getType().getEnvironmentType())).thenReturn(false);
//
//        ProductSellerService productSellerService = new ProductSellerService(mock1);
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSellerService.getProductSeller(1L);
//        });
//
//        String expectedMessage = "Verifique se os produtos pertencem ao mesmo tipo do setor ao qual deseja armazenar!";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}

