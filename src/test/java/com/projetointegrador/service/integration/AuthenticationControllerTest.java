//package com.projetointegrador.service.integration;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class AuthenticationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldAuthentication() throws Exception {
//
//        String payLoad = "{\n" +
//                "    \"user\": \"wesley\",\n" +
//                "    \"senha\": \"123\"\n" +
//                "}";
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("http://localhost:8090/api/v1/auth")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(payLoad))
//                        .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//}
