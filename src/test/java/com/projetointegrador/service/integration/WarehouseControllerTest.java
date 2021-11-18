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
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldInsert() throws Exception {

        String payLoad = "{\n" +
                "    \"warehouseCode\":\"MLB-411\",\n" +
                "    \"description\":\"Teste de cadastro\"\n" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8090/api/v1/warehouse/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldGetWarehouseByProductId() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/warehouse/listWarehouseByProductId/MLB-129"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
