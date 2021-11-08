package com.projetointegrador.service;

import com.projetointegrador.entity.Address;
import com.projetointegrador.entity.Buyer;
import com.projetointegrador.entity.Representative;
import com.projetointegrador.entity.Seller;
import com.projetointegrador.repository.BuyerPersistence;
import com.projetointegrador.repository.RepresentativePersistence;
import com.projetointegrador.repository.SellerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepresentativeServiceTest {

    @Test
    void mustInsertSeller() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        RepresentativeService mock = mock(RepresentativeService.class);
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "33377799955", "Wes", address);

        when(mock.insert(any(Representative.class))).thenReturn(representative);

        RepresentativeService representativeService = new RepresentativeService(mock1);
        representativeService.insert(representative);
        assertNotNull(representative.getRepresentativeId());
    }

    @Test
    void mustNotInsertSeller() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        String cpf = "33399977788";
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "33399977788", "Wes", address);
        when(mock1.findByCpf(cpf)).thenReturn(Optional.of(representative));

        RepresentativeService representativeService = new RepresentativeService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            representativeService.insert(representative);
        });

        String expectedMessage = "Cpf já existente";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }



    @Test
    void mustgetByIdSeller(){
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Optional<Representative> representative = Optional.of(new Representative(1L, "33399977788", "Wes", address));
        when(mock1.findById(1L)).thenReturn(representative);

        RepresentativeService representativeService = new RepresentativeService(mock1);
        Representative representative1 = representativeService.getByIdRepresentative(1L);
        assertNotNull(representative1.getRepresentativeId());
    }
}