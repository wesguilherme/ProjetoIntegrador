package com.projetointegrador.service.unit;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ShippingPersistence;
import com.projetointegrador.repository.StatesPersistence;
import com.projetointegrador.repository.WarehousePersistence;
import com.projetointegrador.service.ShippingService;
import com.projetointegrador.service.StatesService;
import com.projetointegrador.service.WarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatesServiceTest {

    @Test
    void shouldInsertShipping() {
        StatesPersistence mock1 = mock(StatesPersistence.class);
        StatesService mock = mock(StatesService.class);

        States states = States.builder().cep("41004145").nome("SP").valorFrete(410.50).build();

        when(mock.insert(any(States.class))).thenReturn(states);

        StatesService statesService = new StatesService(mock1);
        statesService.insert(states);
        assertNotNull(states.getCep());
    }

    @Test
    void shouldNotPresentShipping() {
        StatesPersistence mock = mock(StatesPersistence.class);

        when(mock.findByCep("41004145")).thenReturn(Optional.empty());

        StatesService statesService = new StatesService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            statesService.getStates("SP");
        });

        String expectedMessage = "Não existe um estado cadastrado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}



