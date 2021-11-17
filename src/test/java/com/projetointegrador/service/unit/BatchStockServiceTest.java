package com.projetointegrador.service;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.BuyerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatchStockServiceTest {


    private Object preparation(){
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        List<BatchStockList> batchStockLists = new ArrayList<>();
        BatchStockList batchStockList = BatchStockList.builder().batchStockNumber(1L).currentQuantity(45).dueDate(LocalDate.now()).build();
        batchStockLists.add(batchStockList);

        SectionResponseDto sectionResponseDto = SectionResponseDto.builder().sectionCode("SEC-123").warehouseCode("MLB-410").build();

        List<BatchStockResponseDto> batchStockResponseDtoList = new ArrayList<>();
        BatchStockResponseDto batchStockResponseDto = BatchStockResponseDto.builder().sectionResponseDto(sectionResponseDto).productId("MLB-123").batchStockList(batchStockLists).build();
        batchStockResponseDtoList.add(batchStockResponseDto);

        Warehouse warehouse = Warehouse.builder().warehouseCode("MLb-410").description("Teste de cadastro").build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).warehouse(warehouse).build();
        InboundOrder inboundOrder = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).section(section).build();
        Product product = Product.builder().productId("MLB-123").name("Uva").description("Caixa de Uva").build();
        ProductSeller productSeller = ProductSeller.builder().productSellerId(1L).volume(10.0).maximumTemperature(5.0).minimumTemperature(1.0).product(product).price(new BigDecimal("20")).build();
        BatchStock batchStock = new BatchStock(1L, LocalDate.now(),LocalDateTime.now(),LocalDate.now(),45,45,null,"4.5",1L,inboundOrder,productSeller);

        when(productServiceMock.getByIdProduct("MLB-123")).thenReturn(product);
        when(productSellerServiceMock.getProductSellerByProduto(product)).thenReturn(productSeller);
        when(batchStockPersistenceMock.findByProductSeller(productSeller)).thenReturn(Collections.singletonList(batchStock));

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock,productServiceMock,productSellerServiceMock);
        List<BatchStockResponseDto> batchStockResponseDtos = Collections.singletonList(batchStockService.listBatchStockWithFilter("MLB-123","L"));

        return 0;
    }

    @Test
    void shouldListBatchStockByProductId(){
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        List<BatchStockList> batchStockLists = new ArrayList<>();
        BatchStockList batchStockList = BatchStockList.builder().batchStockNumber(1L).currentQuantity(45).dueDate(LocalDate.now()).build();
        batchStockLists.add(batchStockList);

        SectionResponseDto sectionResponseDto = SectionResponseDto.builder().sectionCode("SEC-123").warehouseCode("MLB-410").build();

        List<BatchStockResponseDto> batchStockResponseDtoList = new ArrayList<>();
        BatchStockResponseDto batchStockResponseDto = BatchStockResponseDto.builder().sectionResponseDto(sectionResponseDto).productId("MLB-123").batchStockList(batchStockLists).build();
        batchStockResponseDtoList.add(batchStockResponseDto);

        Warehouse warehouse = Warehouse.builder().warehouseCode("MLb-410").description("Teste de cadastro").build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).warehouse(warehouse).build();
        InboundOrder inboundOrder = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).section(section).build();
        Product product = Product.builder().productId("MLB-123").name("Uva").description("Caixa de Uva").build();
        ProductSeller productSeller = ProductSeller.builder().productSellerId(1L).volume(10.0).maximumTemperature(5.0).minimumTemperature(1.0).product(product).price(new BigDecimal("20")).build();
        BatchStock batchStock = new BatchStock(1L, LocalDate.now(),LocalDateTime.now(),LocalDate.now(),45,45,null,"4.5",1L,inboundOrder,productSeller);

        when(productServiceMock.getByIdProduct("MLB-123")).thenReturn(product);
        when(productSellerServiceMock.getProductSellerByProduto(product)).thenReturn(productSeller);
        when(batchStockPersistenceMock.findByProductSeller(productSeller)).thenReturn(Collections.singletonList(batchStock));

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock,productServiceMock,productSellerServiceMock);
        List<BatchStockResponseDto> batchStockResponseDtos = Collections.singletonList(batchStockService.listBatchStockByProductId("MLB-123"));
        assertEquals(1, batchStockResponseDtos.size());
    }

    @Test
    void shouldlistBatchStockWithFilterL(){
        preparation();
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock,productServiceMock,productSellerServiceMock);
        List<BatchStockResponseDto> batchStockResponseDtos = Collections.singletonList(batchStockService.listBatchStockWithFilter("MLB-123","L"));

        assertEquals(1, batchStockResponseDtos.size());

    }

    @Test
    void shouldlistBatchStockWithFilterC(){
        preparation();
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock,productServiceMock,productSellerServiceMock);
        List<BatchStockResponseDto> batchStockResponseDtos = Collections.singletonList(batchStockService.listBatchStockWithFilter("MLB-123","C"));

        assertEquals(1, batchStockResponseDtos.size());

    }

    @Test
    void shouldlistBatchStockWithFilterF(){
        preparation();
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock,productServiceMock,productSellerServiceMock);
        List<BatchStockResponseDto> batchStockResponseDtos = Collections.singletonList(batchStockService.listBatchStockWithFilter("MLB-123","F"));

        assertEquals(1, batchStockResponseDtos.size());

    }

    @Test
    void shouldgetBatchStockByProductSeller() {
        BatchStockPersistence mock1 = mock(BatchStockPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));

        List<BatchStock> batchStockList = new ArrayList<>();
        BatchStock batchStock = new BatchStock(1L,LocalDate.now(), LocalDateTime.now(), LocalDate.now(), 25, 50, null, null, 1L, null, productSeller);
        batchStockList.add(batchStock);
        when(mock1.findByProductSeller(any(ProductSeller.class))).thenReturn(batchStockList);

        BatchStockService batchStockService = new BatchStockService(mock1);
        List<BatchStock> batchStock1 = batchStockService.getBatchStockByProductSeller(productSeller);
        assertNotNull(batchStock1.get(0).getBatchStockId());
    }

    @Test
    void shouldNotgetBatchStockByProductSeller() {
        BatchStockPersistence mock = mock(BatchStockPersistence.class);

        BatchStockService batchStockService = new BatchStockService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            batchStockService.getBatchStockByProductSeller(ProductSeller.builder().build());
        });

        assertEquals("Não existe batchStock para esse produto.", exception.getMessage());
    }

    @Test
    void shouldVerifyProductInBatchStock(){
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);
        BatchStockService batchStockServiceMock = mock(BatchStockService.class);

        Product product = Product.builder().productId("MLB-123").name("Uva").description("Caixa de Uva").type(null).build();
        ProductSeller productSeller = ProductSeller.builder().productSellerId(1L).volume(10.0).maximumTemperature(5.0).minimumTemperature(1.0).seller(null).product(product).price(new BigDecimal("20")).build();

        List<BatchStock> batchStocks = new ArrayList<>();
        BatchStock batchStock = new BatchStock(1L, LocalDate.now().plusDays(28),LocalDateTime.now(),LocalDate.now(),45,45,null,"4.5",1L,null,productSeller);
        batchStocks.add(batchStock);

        List<ProductItemDto> productItemDtos = new ArrayList<>();
        ProductItemDto productItemDto = ProductItemDto.builder().productId("MLB-123").quantity(0).build();
        productItemDtos.add(productItemDto);

        when(productServiceMock.getByIdProduct("MLB-123")).thenReturn(product);
        when(productSellerServiceMock.getProductSellerByProduto(product)).thenReturn(productSeller);
        when(batchStockPersistenceMock.findByProductSeller(any(ProductSeller.class))).thenReturn(batchStocks);

        BatchStockService batchStockService1 = new BatchStockService(batchStockPersistenceMock,productServiceMock, productSellerServiceMock);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            batchStockService1.verifyProductInBatchStock(Collections.singletonList(productItemDto));
        });

        assertEquals("Validade do produto: MLB-123 é inferior a 3 semanas", exception.getMessage());
    }

    @Test
    void shouldNotVerifyProductInBatchStock() {
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        Product product = Product.builder().productId("MLB-123").name("Uva").description("Caixa de Uva").type(null).build();
        ProductSeller productSeller = ProductSeller.builder().productSellerId(1L).volume(10.0).maximumTemperature(5.0).minimumTemperature(1.0).seller(null).product(product).price(new BigDecimal("20")).build();

        List<BatchStock> batchStocks = new ArrayList<>();
        BatchStock batchStock = new BatchStock(1L, LocalDate.now().plusDays(28), LocalDateTime.now(), LocalDate.now(), 45, 45, null, "4.5", 1L, null, productSeller);
        batchStocks.add(batchStock);

        List<ProductItemDto> productItemDtos = new ArrayList<>();
        ProductItemDto productItemDto = ProductItemDto.builder().productId("MLB-123").quantity(1000).build();
        productItemDtos.add(productItemDto);

        when(productServiceMock.getByIdProduct("MLB-123")).thenReturn(product);
        when(productSellerServiceMock.getProductSellerByProduto(product)).thenReturn(productSeller);
        when(batchStockPersistenceMock.findByProductSeller(any(ProductSeller.class))).thenReturn(batchStocks);

        BatchStockService batchStockService1 = new BatchStockService(batchStockPersistenceMock, productServiceMock, productSellerServiceMock);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            batchStockService1.verifyProductInBatchStock(Collections.singletonList(productItemDto));
        });

        assertEquals("Não existe estoque para este produto: MLB-123", exception.getMessage());
    }

    @Test
    void shouldListBatchStockBySection() {
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        List<BatchStockPersistence.BatchStockListByDays> batchStocks = new ArrayList<>();
        BatchStockPersistence.BatchStockListByDays batchStockListByDays = new BatchStockPersistence.BatchStockListByDays() {

            @Override
            public Long getBatch_stock_number() {
                return 1L;
            }

            @Override
            public String getProduct_id() {
                return "MLB-123";
            }

            @Override
            public String getEnvironment_type() {
                return "CONGELADOS";
            }

            @Override
            public LocalDate getDue_date() {
                return LocalDate.now();
            }

            @Override
            public Integer getCurrent_quantity() {
                return 100;
            }
        };
        batchStocks.add(batchStockListByDays);

        when(batchStockPersistenceMock.listbatchByDays(anyString(), anyInt())).thenReturn(batchStocks);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock);
        List<BatchStockPersistence.BatchStockListByDays> batchStock1 = batchStockService.batchStockInSection("SEC-123", 15);
        assertEquals(1, batchStock1.size());
    }

    @Test
    void shouldListBatchStockByFilter() {
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        List<BatchStockPersistence.BatchStockListByFilter> batchStocks = new ArrayList<>();
        BatchStockPersistence.BatchStockListByFilter batchStockListByFilter = new BatchStockPersistence.BatchStockListByFilter() {

            @Override
            public Long getBatch_stock_number() {
                return 1L;
            }

            @Override
            public String getProduct_id() {
                return "MLB-123";
            }

            @Override
            public String getEnvironment_type() {
                return "CONGELADOS";
            }

            @Override
            public LocalDate getDue_date() {
                return LocalDate.now();
            }

            @Override
            public Integer getCurrent_quantity() {
                return 100;
            }
        };
        batchStocks.add(batchStockListByFilter);

        when(batchStockPersistenceMock.listbatchByFilter(anyInt(), anyLong(), any())).thenReturn(batchStocks);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock);
        List<BatchStockPersistence.BatchStockListByFilter> batchStock1 = batchStockService.batchStockListWithFilter(15, 2L, Pageable.unpaged());
        assertEquals(1, batchStock1.size());
    }

    @Test
    void shouldGetBatchStockByProductId(){

        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        BatchStockService batchStockServiceMock = mock(BatchStockService.class);
        BatchStockPersistence.BatchStockByProductId batchStockProductId = new BatchStockPersistence.BatchStockByProductId() {
            @Override
            public Long getBatch_stock_id() {
                return 1L;
            }

            @Override
            public String getProduct_id() {
                return "MLB-129";
            }

            @Override
            public Integer getCurrent_quantity() {
                return 10;
            }
        };

        when(batchStockPersistenceMock.batchStockByProductId(anyString())).thenReturn(batchStockProductId);

        ProductItemCartDto productItemCartDto = ProductItemCartDto.builder().productId("MLB-129").quantity(10).batchStockId(1L).build();

        when(batchStockServiceMock.getBatchStockByProductId(anyString())).thenReturn(productItemCartDto);

        BatchStockService batchStockService = new BatchStockService(batchStockPersistenceMock);

        ProductItemCartDto productItemCartDto1 = batchStockService.getBatchStockByProductId("MLB-129");
        assertEquals("MLB-129", productItemCartDto1.getProductId());
    }

    @Test
    void shouldGetBatchStockById ( ) {
        BatchStockPersistence mock = mock(BatchStockPersistence.class);

        BatchStock batchStock = BatchStock.builder().batchStockNumber(1L).batchStockId(1L).currentQuantity(10).dueDate(LocalDate.now()).build();

        when(mock.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock));

        BatchStockService batchStockService = new BatchStockService(mock);
        Optional<BatchStock> batchStock1 = batchStockService.getBatchStockById(1L);
        assertNotNull(batchStock1);
    }

    @Test
    void shouldNotGetBatchStockById ( ) {
        BatchStockPersistence mock = mock(BatchStockPersistence.class);

        BatchStockService batchStockService = new BatchStockService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            batchStockService.getBatchStockById(1L);
        });

        assertEquals("Não existe batchStock para esse produto.", exception.getMessage());
    }
}

