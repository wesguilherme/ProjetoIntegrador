package com.projetointegrador.service.unit;


import com.projetointegrador.entity.Address;
import com.projetointegrador.entity.Buyer;
import com.projetointegrador.repository.BuyerPersistence;
import com.projetointegrador.service.BuyerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyerServiceTest {

    @Test
    void shouldInsertBuyer ( ) {
        BuyerPersistence mock1 = mock(BuyerPersistence.class);
        BuyerService mock = mock(BuyerService.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Buyer buyer = new Buyer(1L, "33377799955", "Wes", address);

        when(mock.insert(any(Buyer.class))).thenReturn(buyer);

        BuyerService buyerService = new BuyerService(mock1);
        buyerService.insert(buyer);
        assertNotNull(buyer.getBuyerId());
    }

    @Test
    void shouldNotInsertBuyer ( ) {
        BuyerPersistence mock1 = mock(BuyerPersistence.class);
        String cpf = "33399977788";
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Buyer buyer = new Buyer(1L, "33399977788", "Wes", address);
        when(mock1.findByCpf(cpf)).thenReturn(buyer);

        BuyerService buyerService = new BuyerService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            buyerService.insert(buyer);
        });

        String expectedMessage = "Cpf já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldGetByIdBuyer ( ) {
        BuyerPersistence mock1 = mock(BuyerPersistence.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Optional<Buyer> buyer = Optional.of(new Buyer(1L, "33399977788", "Wes", address));
        when(mock1.findById(1L)).thenReturn(buyer);

        BuyerService buyerService = new BuyerService(mock1);
        Buyer buyer1 = buyerService.getByIdBuyer(1L);
        assertNotNull(buyer1.getBuyerId());

    }

    @Test
    void shouldNotGetProductSellerByProduto ( ) {
        BuyerPersistence mock = mock(BuyerPersistence.class);

        BuyerService buyerService = new BuyerService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            buyerService.getByIdBuyer(1l);
        });
        assertEquals("Não existe comprador cadastrado!", exception.getMessage());
    }
}