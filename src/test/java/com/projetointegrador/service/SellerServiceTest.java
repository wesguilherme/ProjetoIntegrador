package com.projetointegrador.service;

import com.projetointegrador.entity.Address;
import com.projetointegrador.entity.Seller;
import com.projetointegrador.repository.SellerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SellerServiceTest {

    @Test
    void mustInsertSeller() {
        SellerPersistence mock1 = mock(SellerPersistence.class);
        SellerService mock = mock(SellerService.class);
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "33377799955", "Wes", address);

        when(mock.insert(any(Seller.class))).thenReturn(seller);

        SellerService sellerService = new SellerService(mock1);
        sellerService.insert(seller);
        assertNotNull(seller.getSellerId());
    }

    @Test
    void mustNotInsertSeller() {
        SellerPersistence mock1 = mock(SellerPersistence.class);
        String cpf = "33399977788";
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "33399977788", "Wes", address);
        when(mock1.findByCpf(cpf)).thenReturn(seller);

        SellerService sellerService = new SellerService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            sellerService.insert(seller);
        });

        String expectedMessage = "Cpf já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void mustgetByIdSeller(){
        SellerPersistence mock1 = mock(SellerPersistence.class);
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Optional<Seller> seller = Optional.of(new Seller(1L, "33399977788", "Wes", address));
        when(mock1.findById(1L)).thenReturn(seller);

        SellerService sellerService = new SellerService(mock1);
        Seller seller1 = sellerService.getByIdSeller(1L);
        assertNotNull(seller1.getSellerId());
    }
}

