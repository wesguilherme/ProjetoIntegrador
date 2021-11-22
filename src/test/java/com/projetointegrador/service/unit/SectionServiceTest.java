package com.projetointegrador.service.unit;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ProductSellerPersistence;
import com.projetointegrador.repository.SectionPersistence;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SectionServiceTest {

    @Test
    void shouldInsert(){
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);

        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();
        Warehouse warehouse = Warehouse.builder().warehouseCode("MLB-410").description("Teste de cadastro").build();
        Address address = Address.builder().street("rua goias").number("44").postalCode("99999-000").city("SP").state("SP").complement("casa").build();
        Representative representative = Representative.builder().representativeId(1L).cpf("111.222.333-44").name("Rafael").address(address).build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).type(type).representative(representative).warehouse(warehouse).build();

        when(sectionPersistenceMock.save(any(Section.class))).thenReturn(section);

        SectionService sectionService = new SectionService(sectionPersistenceMock);
        Section section1 = sectionService.insert(section);
        assertNotNull (section1.getSectionCode());

    }

    @Test
    void shouldnotInsert() throws RuntimeException{
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);

        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();
        Address address = Address.builder().street("rua goias").number("44").postalCode("99999-000").city("SP").state("SP").complement("casa").build();
        Representative representative = Representative.builder().representativeId(null).cpf("111.222.333-44").name("Rafael").address(address).build();
        Warehouse warehouse = Warehouse.builder().warehouseCode(null).description("Teste de cadastro").build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).type(type).representative(representative).warehouse(warehouse).build();

        when(sectionPersistenceMock.save(any(Section.class))).thenReturn(section);

        SectionService sectionService = new SectionService(sectionPersistenceMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            sectionService.insert(section);
        });

        String expectedMessage = "Representante ou armazém não existe!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void shouldGetSectionByCode() {
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro", address);

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
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro", address);

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
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro", address);

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

    @Test
    void shouldVerifyValidSection() throws NullPointerException {
        SectionPersistence mock = mock(SectionPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro", address);

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
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro", address);

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
    void shouldVerifyAvailableSpace() {
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

