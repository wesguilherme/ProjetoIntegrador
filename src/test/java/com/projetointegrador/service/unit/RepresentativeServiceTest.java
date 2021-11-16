package com.projetointegrador.service.unit;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BuyerPersistence;
import com.projetointegrador.repository.RepresentativePersistence;
import com.projetointegrador.repository.SectionPersistence;
import com.projetointegrador.repository.SellerPersistence;
import com.projetointegrador.service.RepresentativeService;
import com.projetointegrador.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepresentativeServiceTest {

    @Test
    void shouldInsertRepresentative() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        RepresentativeService mock = mock(RepresentativeService.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "33377799955", "Wes", address);

        when(mock.insert(any(Representative.class))).thenReturn(representative);

        RepresentativeService representativeService = new RepresentativeService(mock1);
        representativeService.insert(representative);
        assertNotNull(representative.getRepresentativeId());
    }

    @Test
    void shouldNotInsertRepresentative() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        String cpf = "33399977788";
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "33399977788", "Wes", address);
        when(mock1.findByCpf(cpf)).thenReturn(Optional.of(representative));

        RepresentativeService representativeService = new RepresentativeService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            representativeService.insert(representative);
        });

        String expectedMessage = "Cpf já existente";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void shouldGetByIdRepresentative() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Optional<Representative> representative = Optional.of(new Representative(1L, "33399977788", "Wes", address));
        when(mock1.findById(1L)).thenReturn(representative);

        RepresentativeService representativeService = new RepresentativeService(mock1);
        Representative representative1 = representativeService.getByIdRepresentative(1L);
        assertNotNull(representative1.getRepresentativeId());
    }

    @Test
    void shouldNotGetByIdRepresentative() {
        RepresentativePersistence mock1 = mock(RepresentativePersistence.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Optional<Representative> representative = Optional.of(new Representative(1L, "33399977788", "Wes", address));
        when(mock1.findById(1L)).thenReturn(representative);

        RepresentativeService representativeService = new RepresentativeService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            representativeService.getByIdRepresentative(2L);
        });

        String expectedMessage = "Não existe representante com esse id!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldVerifyRepresentativeBelongsToWarehouse() {
        SectionService sectionServiceMock = mock(SectionService.class);
        SectionPersistence sectionPersistenceMock = mock(SectionPersistence.class);
        RepresentativePersistence representativePersistence = mock(RepresentativePersistence.class);

        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();
        Warehouse warehouse = Warehouse.builder().warehouseCode("MLB-410").description("Teste de cadastro").build();
        Address address = Address.builder().street("rua goias").number("44").postalCode("99999-000").city("SP").state("SP").complement("casa").build();
        Representative representative = Representative.builder().representativeId(1L).cpf("111.222.333-44").name("Rafael").address(address).build();

        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).type(type).representative(representative).warehouse(warehouse).build();

        when(sectionServiceMock.getRepresentative(1L)).thenReturn(section);
        when(sectionPersistenceMock.findBySectionCode(anyString())).thenReturn(Optional.ofNullable(section));

        RepresentativeService representativeService1 = new RepresentativeService(representativePersistence, sectionServiceMock);
        Boolean representative1 = representativeService1.verifyRepresentativeBelongsToWarehouse(1L);
        assertTrue(representative1);
    }

    @Test
    void shouldNotVerifyRepresentativeBelongsToWarehouse() {

        SectionService sectionServiceMock = mock(SectionService.class);

        RepresentativePersistence representativePersistence = mock(RepresentativePersistence.class);

        RepresentativeService representativeService1 = new RepresentativeService(representativePersistence, sectionServiceMock);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            representativeService1.verifyRepresentativeBelongsToWarehouse(1L);
        });

        String expectedMessage = "Não existe armazem vinculado a esse representante";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}