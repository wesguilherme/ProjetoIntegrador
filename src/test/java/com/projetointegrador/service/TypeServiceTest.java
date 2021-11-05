package com.projetointegrador.service;

import com.projetointegrador.entity.Type;
import com.projetointegrador.repository.TypePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TypeServiceTest {

    @Test
    void shouldGetTypeByTypeId() {
        TypePersistence mock1 = mock(TypePersistence.class);
        Type type = new Type(3L, "FF", "Congelados");
        when(mock1.findByInitials("FF")).thenReturn(type);

        TypeService typeService = new TypeService(mock1);
        Type type1 = typeService.typePersistence.findByInitials("FF");
        assertNotNull(type1.getTypeId());
    }

    @Test
    void shouldNotGetTypeByTypeId() {
        TypePersistence mock1 = mock(TypePersistence.class);
        Type type = new Type(3L, "FF", "Congelados");
        when(mock1.findByInitials("FF")).thenReturn(type);

        TypeService typeService = new TypeService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            typeService.getTypeByTypeId(3L);
        });

        String expectedMessage = "Não existe um tipo para o valor passado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
