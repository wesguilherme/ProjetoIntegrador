package com.projetointegrador.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetointegrador.dto.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeTestClass
    public TokenDto auth() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String payLoadLogin = "{\n" +
                "    \"user\": \"wesley\",\n" +
                "    \"senha\": \"123\"\n" +
                "}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8090/api/v1/auth")
                        .content(payLoadLogin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        TokenDto tokenDTO = mapper.readValue(response, TokenDto.class);

        return tokenDTO;
    }

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
                         .header("Authorization", "Bearer " + auth().getToken())
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
                MockMvcRequestBuilders.put("http://localhost:8090/api/v1/nivel/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + auth().getToken())
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldlistNivel() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/nivel/list/")
                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistNivelBuyerId() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/nivel/list/nivelBuyer/2")
                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistAllPurchaseBuyer() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/product/orders/buyer/1")
                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotFoundPurchaseBuyer() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/product/orders/buyer/10")
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

