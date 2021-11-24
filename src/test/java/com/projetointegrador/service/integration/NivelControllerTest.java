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
public class NivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldInsert() throws Exception {

        String payLoad = "{\n" +
                "   \"valorTotalDeCompra\" : 500.00 ,\n" +
                "   \"percentualDesconto\" : 40 ,\n" +
                "   \"beneficios\":\"Desconto, Frete Grátis, um Kit Hortifruti e um kit de Condimentos\"\n"+
                "}";

         mockMvc.perform(
                 MockMvcRequestBuilders.post("http://localhost:8090/api/v1/nivel/insert")
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(payLoad))
                         .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldUpdate() throws Exception {

        String payLoad = "{\n" +
                "   \"valorTotalDeCompra\" : 500.00 ,\n" +
                "   \"percentualDesconto\" : 40 ,\n" +
                "   \"beneficios\":\"Desconto, Frete Grátis, um Kit Hortifruti e um kit de Condimentos\"\n"+
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8090/api/v1/nivel/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldlistNivel() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/nivel/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistNivelBuyerId() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/nivel/list/nivelBuyer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistAllPurchaseBuyer() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/product/orders/buyer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

