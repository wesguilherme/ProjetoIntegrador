package com.projetointegrador.service.unit;

import com.projetointegrador.entity.User;
import com.projetointegrador.repository.UserPersistence;
import com.projetointegrador.service.AuthenticationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Test
    void loadUserByUsername() {

        UserPersistence mock1 = mock(UserPersistence.class);
        AuthenticationService mock = mock(AuthenticationService.class);
        User user = new User("wes", "123", true);

        when(mock1.findByUser("wes")).thenReturn(user);

        AuthenticationService authenticationService = new AuthenticationService(mock1);
        User user1 = (User) authenticationService.loadUserByUsername("wes");
        assertEquals("wes", user1.getUsername());

//    SellerService sellerService = new SellerService(mock1);
//            sellerService.insert(seller);
//    assertNotNull(seller.getSellerId());
    }
}
