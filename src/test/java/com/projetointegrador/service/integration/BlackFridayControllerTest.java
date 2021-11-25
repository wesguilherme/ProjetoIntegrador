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
public class BlackFridayControllerTest {

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
                "    \"productId\": \"MLB-129\",\n" +
                "    \"discount\": 0.90,\n" +
                "    \"initialDate\": \"2021-11-22\",\n" +
                "    \"finalDate\": \"2021-11-26\"\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8090/api/v1/fresh-products/blackfriday/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad)
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldGetBlackFridaysProductList() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/blackfriday/list")
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdate() throws Exception {

        String payLoad = "{\n" +
                "    \"productId\": \"MLB-129\",\n" +
                "    \"discount\": 0.10,\n" +
                "    \"initialDate\": \"2021-11-22\",\n" +
                "    \"finalDate\": \"2021-11-26\"\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("http://localhost:8090/api/v1/fresh-products/blackfriday/update/MLB-129")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad)
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldDelete() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("http://localhost:8090/api/v1/fresh-products/blackfriday/delete/MLB-129")
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
