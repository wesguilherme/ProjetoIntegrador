package com.projetointegrador.service.unit;

import com.projetointegrador.dto.BlackFridayResponseDto;
import com.projetointegrador.entity.BlackFriday;
import com.projetointegrador.repository.BlackFridayPersistence;
import com.projetointegrador.service.BlackFridayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BlackFridayServiceTest {

    @Test
    void shouldInsertSeller() {
        BlackFridayPersistence mock1 = mock(BlackFridayPersistence.class);
        BlackFridayService mock = mock(BlackFridayService.class);
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();

        when(mock1.save(blackFriday)).thenReturn(blackFriday);

        BlackFridayService blackFridayService = new BlackFridayService(mock1);
        BlackFriday blackFriday1 = blackFridayService.insert(blackFriday);
        assertNotNull(blackFriday1.getProductId());
    }

    @Test
    void shouldListProduct() {
        BlackFridayPersistence blackFridayPersistenceMock = mock(BlackFridayPersistence.class);
        BlackFridayService blackFridayServiceMock = mock(BlackFridayService.class);

        List<BlackFriday> blackFridays = new ArrayList<>();
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();
        blackFridays.add(blackFriday);

        List<BlackFridayResponseDto> blackFridayResponseDtoList = new ArrayList<>();
        BlackFridayResponseDto blackFridayResponseDto = BlackFridayResponseDto.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();
        blackFridayResponseDtoList.add(blackFridayResponseDto);

        when(blackFridayPersistenceMock.findAll()).thenReturn(blackFridays);
        when(blackFridayServiceMock.listProduct()).thenReturn(blackFridayResponseDtoList);

        BlackFridayService blackFridayService = new BlackFridayService(blackFridayPersistenceMock);

        List<BlackFridayResponseDto> blackFridayResponseDtoList1 = blackFridayService.listProduct();
        assertEquals(1, blackFridayResponseDtoList1.size());
    }

    @Test
    void shouldUpdate() {
        BlackFridayPersistence blackFridayPersistenceMock = mock(BlackFridayPersistence.class);

        List<BlackFriday> blackFridays = new ArrayList<>();
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();
        blackFridays.add(blackFriday);

        when(blackFridayPersistenceMock.findByProductId(anyString())).thenReturn(Optional.ofNullable(blackFriday));
        when(blackFridayPersistenceMock.save(any(BlackFriday.class))).thenReturn(blackFriday);

        BlackFridayService blackFridayService = new BlackFridayService(blackFridayPersistenceMock);
        BlackFriday blackFriday1 = blackFridayService.update(blackFriday, "MLB-123");
        assertNotNull(blackFriday1.getProductId());
    }

    @Test
    void shouldNotUpdate() {
        BlackFridayPersistence blackFridayPersistenceMock = mock(BlackFridayPersistence.class);

        when(blackFridayPersistenceMock.findByProductId(anyString())).thenReturn(Optional.empty());
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();
        BlackFridayService blackFridayService = new BlackFridayService(blackFridayPersistenceMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            blackFridayService.update(blackFriday, "MLB-123");
        });

        assertEquals("Não existe esse produto no hall de descontos!", exception.getMessage());
    }

    @Test
    void shouldDelete() {
        BlackFridayPersistence mockBlackFridayPersistence = mock(BlackFridayPersistence.class);
        BlackFridayService service = new BlackFridayService(mockBlackFridayPersistence);
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();

        when(mockBlackFridayPersistence.findByProductId("MLB-123")).thenReturn(Optional.ofNullable(blackFriday));
        service.delete(blackFriday.getProductId());
        verify(mockBlackFridayPersistence, times(1)).delete(any(BlackFriday.class));
    }

    @Test
    void shouldNotDelete() {
        BlackFridayPersistence mockBlackFridayPersistence = mock(BlackFridayPersistence.class);
        BlackFridayService service = new BlackFridayService(mockBlackFridayPersistence);

        when(mockBlackFridayPersistence.findByProductId("MLB-123")).thenReturn(Optional.ofNullable(null));
        service.delete(any(String.class));
        verify(mockBlackFridayPersistence, times(0)).delete(any(BlackFriday.class));
    }

    @Test
    void shouldGetByIdProduct() {
        BlackFridayPersistence mock1 = mock(BlackFridayPersistence.class);
        BlackFriday blackFriday = BlackFriday.builder().productId("MLB-123").discount(new BigDecimal("0.6")).initialDate(LocalDate.of(2021, 11, 22)).finalDate(LocalDate.of(2021, 11, 26)).build();
        when(mock1.findByProductId("MLB-123")).thenReturn(Optional.ofNullable(blackFriday));

        BlackFridayService blackFridayService = new BlackFridayService(mock1);
        BlackFriday blackFriday1 = blackFridayService.getByIdProduct("MLB-123");
        assertNotNull(blackFriday1.getProductId());
    }
}
