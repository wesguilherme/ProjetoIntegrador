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
public class BatchStockControllerTest {

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
    public void shouldlistBatchStockByProductId() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/listById/MLB-129")
                                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistBatchStockWithFilter() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/list/MLB-129/F")
                                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldBatchStockInSection() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/due-date/SEC-126/15")
                                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldBatchStockListWithFilter() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/due-date/list?quantityOfDays=15&typeId=2&sort=due_date,ASC")
                                .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}