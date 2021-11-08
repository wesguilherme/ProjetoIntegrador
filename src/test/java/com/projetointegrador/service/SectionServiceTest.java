package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.SectionDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SectionServiceTest {

    @Test
    void shouldInsert() {
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
        SectionDto sectionDto = new SectionDto("SEC-123", 200.0, 1L, 2L, "MLB-410");

        Mockito.when(representativeServiceMock.getByIdRepresentative(2L)).thenReturn(representative);
        Mockito.when(warehouseServiceMock.getByCode("MLB-410")).thenReturn(warehouse);

        when(sectionServiceMock.insert(any(SectionDto.class))).thenReturn(section);

        SectionService sectionService = new SectionService(representativeServiceMock, warehouseServiceMock, typeServiceMock, sectionPersistenceMock);
        sectionService.insert(sectionDto);
        assertNotNull(section.getSectionCode());

    }

    @Test
    void shouldnotInsert() throws RuntimeException {
        RepresentativeService representativeServiceMock = mock(RepresentativeService.class);
        WarehouseService warehouseServiceMock = mock(WarehouseService.class);
        TypeService typeServiceMock = mock(TypeService.class);

        Mockito.when(representativeServiceMock.getByIdRepresentative(Mockito.anyLong())).thenReturn(new Representative());
        Mockito.when(warehouseServiceMock.getByCode(Mockito.anyString())).thenReturn(new Warehouse());

        SectionService sectionService = new SectionService(representativeServiceMock, warehouseServiceMock, typeServiceMock);

        SectionDto sectionDto = new SectionDto("SEC-123", 200.0, 1L, 2L, "MLB-410");
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
    void shouldnotGetSectionByCode() throws RuntimeException {
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
    void shouldGetRepresentative() {
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
    void shouldnotGetRepresentative() throws RuntimeException {
        SectionPersistence mock = mock(SectionPersistence.class);

        SectionService sectionService = new SectionService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.getRepresentative(1L);
        });

        String expectedMessage = "Não existe Representative para essa busca!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldVerifyValidSection() throws NullPointerException {
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Section> section = Optional.of(new Section("SEC-123", 200.0, 30.0, type, representative, warehouse));

        when(mock.findBySectionCode("SEC-123")).thenReturn(section);
        SectionService sectionService = new SectionService(mock);
        Boolean verify = sectionService.verifyValidSection("SEC-123");
        assertTrue(verify);
    }

    @Test
    void shouldNotVerifyValidSection() throws NullPointerException {
        SectionPersistence mock = mock(SectionPersistence.class);

        when(mock.findBySectionCode("SEC-123")).thenReturn(Optional.empty());
        SectionService sectionService = new SectionService(mock);
        Boolean verify = sectionService.verifyValidSection("SEC-123");
        assertFalse(verify);
    }

    @Test
    void shouldVerifySpaceCapacity() throws NullPointerException{
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);
        ProductSellerPersistence productSellerPersistenceMock = mock(ProductSellerPersistence.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        Double totalVolumeProduct = 10d;

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L,"111.222.333-44","Rafael",address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva",type);
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        ProductSeller productSeller = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));
        Section section = new Section("SEC-123", 200.0, 30.0, type, representative, warehouse);

        List<BatchStockDto> batchStockDto = new ArrayList<>();

        when(productSellerPersistenceMock.findById(1L)).thenReturn(Optional.of(productSeller));
        when(productSellerServiceMock.getProductSeller(1L)).thenReturn(productSeller);

        SectionService sectionService = new SectionService(productSellerServiceMock,sectionPersistenceMock);

        sectionService.verifyAvailableSpace(section,batchStockDto);
        assertEquals(10, totalVolumeProduct);
    }

    @Test
    void shouldVerifyAvaiableSpace() {
        Section s = Section.builder().usedSpace(100d).totalCapacity(90d).build();
        List<BatchStockDto> listaDTO = new ArrayList<>();
        BatchStockDto stockDto = BatchStockDto.builder().productSellerId(1L).build();
        listaDTO.add(stockDto);


        ProductSellerService mock = mock(ProductSellerService.class);
        Mockito.when(mock.getProductSeller(Mockito.anyLong())).thenReturn(ProductSeller.builder().volume(100d).build());
        SectionService sectionService = new SectionService(mock);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.verifyAvailableSpace(s, listaDTO);
        });
        String expectedMessage = "Setor sem espaço disponível";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldnotVolumeSpace() throws NullPointerException{
        Section s = Section.builder().usedSpace(90d).totalCapacity(100d).build();
        List<BatchStockDto> listaDTO = new ArrayList<>();
        BatchStockDto stockDto = BatchStockDto.builder().productSellerId(1L).build();
        listaDTO.add(stockDto);

        ProductSellerService mock = mock(ProductSellerService.class);
        Mockito.when(mock.getProductSeller(Mockito.anyLong())).thenReturn(ProductSeller.builder().volume(100d).build());
        SectionService sectionService = new SectionService(mock);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.verifyAvailableSpace(s, listaDTO);
        });
        String expectedMessage = "O volume não cabe nesse setor";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void shouldVerifyEqualType() throws NullPointerException{
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);
        ProductSellerPersistence mock1 =mock(ProductSellerPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L,"111.222.333-44","Rafael",address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva",type);

        ProductSeller productSeller = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));

        when(mock1.findById(1L)).thenReturn(Optional.of(productSeller));

        ProductSellerService productSellerService = new ProductSellerService(mock1);
        SectionService sectionService = new SectionService(productSellerService, sectionPersistenceMock);
        Boolean verify = sectionService.verifyEqualType("REFRIGERADOS", 1L);
        assertTrue(verify);
    }

    @Test
    void shouldNotverifyEqualType() throws NullPointerException{
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);
        ProductSellerPersistence mock1 =mock(ProductSellerPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L,"111.222.333-44","Rafael",address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva",type);

        ProductSeller productSeller = new ProductSeller(1L,10.0,5.0,1.0, seller, product,new BigDecimal("20"));

        when(mock1.findById(1L)).thenReturn(Optional.of(productSeller));

        ProductSellerService productSellerService = new ProductSellerService(mock1);
        SectionService sectionService = new SectionService(productSellerService, sectionPersistenceMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.verifyEqualType("CONGELADOS", 1L);
        });

        String expectedMessage = "Verifique se os produtos pertencem ao mesmo tipo do setor ao qual deseja armazenar!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

