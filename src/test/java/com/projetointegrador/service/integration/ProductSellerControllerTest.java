package com.projetointegrador.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductSellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldInsert() throws Exception {

        String payLoad = "{\n" +
                "\t\"volume\": 80,\n" +
                "\t\"maximumTemperature\": 23,\n" +
                "\t\"minimumTemperature\": 9.5,\n" +
                "    \"sellerId\": 1,\n" +
                "    \"productId\": \"MLB-122\",\n" +
                "    \"price\":20\n" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8090/api/v1/product-seller/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}



