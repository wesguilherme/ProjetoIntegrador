package com.projetointegrador.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BatchStockControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldlistBatchStockByProductId() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/listById/MLB-122"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldlistBatchStockWithFilter() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/list/MLB-120/F"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldBatchStockInSection() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/due-date/SEC-412/15"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldBatchStockListWithFilter() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/batchStock/due-date/list?quantityOfDays=15&typeId=2&sort=due_date,ASC"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
