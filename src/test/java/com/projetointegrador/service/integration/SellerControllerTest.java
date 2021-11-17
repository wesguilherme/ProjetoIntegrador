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
public class SellerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldInsert() throws Exception {

        String payLoad = "{\n" +
                "    \"name\": \"Ana\",\n" +
                "    \"cpf\": \"150.555.303-960\",\n" +
                "    \"address\": \n" +
                "        {\n" +
                "            \"street\": \"Rua das Bananeiras\",\n" +
                "            \"number\": \"80\",\n" +
                "            \"postalCode\": \"123455000\",\n" +
                "            \"city\": \"Mogi das Cruzes\",\n" +
                "            \"state\": \"São Paulo\",\n" +
                "            \"complement\": \"Casa\"\n" +
                "        }\n" +
                "}";
        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8090/api/v1/seller/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}