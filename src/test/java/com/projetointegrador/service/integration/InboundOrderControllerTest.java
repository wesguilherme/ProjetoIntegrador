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
public class InboundOrderControllerTest {

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
                "    \"orderNumber\": 45,\n" +
                "    \"orderDate\":\"1998-07-23\",\n" +
                "    \"sectionCode\": \"SEC-126\",\n" +
                "    \"batchStockDto\":[\n" +
                "        {\n" +
                "            \"productSellerId\": 1,\n" +
                "            \"currentTemperature\": 25,\n" +
                "            \"minimumTemperature\": 9.5,\n" +
                "            \"initialQuantity\": 100,\n" +
                "            \"currentQuantity\": 100,\n" +
                "            \"manufacturingDate\": \"1998-07-23\",\n" +
                "            \"manufacturingTime\": \"2021-09-15T12:23:37.206794\",\n" +
                "            \"dueDate\": \"2021-11-23\",\n" +
                "            \"batchStockNumber\": 77\n" +
                "        },\n" +
                "        {\n" +
                "            \"productSellerId\": 1,\n" +
                "            \"currentTemperature\": 25,\n" +
                "            \"minimumTemperature\": 9.5,\n" +
                "            \"initialQuantity\": 100,\n" +
                "            \"currentQuantity\": 100,\n" +
                "            \"manufacturingDate\": \"1998-07-23\",\n" +
                "            \"manufacturingTime\": \"2021-09-15T12:23:37.206794\",\n" +
                "            \"dueDate\": \"2021-11-29\",\n" +
                "            \"batchStockNumber\": 99\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8090/api/v1/fresh-products/inboundorder/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + auth().getToken())
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldUpdate() throws Exception {

        String payLoad = "{\n" +
                "    \"orderNumber\": 40,\n" +
                "    \"orderDate\":\"2021-07-23\",\n" +
                "    \"sectionCode\": \"SEC-125\",\n" +
                "    \"batchStockDto\":[\n" +
                "        {\n" +
                "            \"productSellerId\": 1,\n" +
                "            \"currentTemperature\": 25,\n" +
                "            \"minimumTemperature\": 8,\n" +
                "            \"initialQuantity\": 100,\n" +
                "            \"currentQuantity\": 80,\n" +
                "            \"manufacturingDate\": \"1998-07-23\",\n" +
                "            \"manufacturingTime\": \"2021-09-15T12:23:37.206794\",\n" +
                "            \"dueDate\": \"2021-11-23\",\n" +
                "            \"batchStockNumber\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"productSellerId\": 1,\n" +
                "            \"currentTemperature\": 25,\n" +
                "            \"minimumTemperature\": 9,\n" +
                "            \"initialQuantity\": 120,\n" +
                "            \"currentQuantity\": 90,\n" +
                "            \"manufacturingDate\": \"1998-07-23\",\n" +
                "            \"manufacturingTime\": \"2021-09-15T12:23:37.206794\",\n" +
                "            \"dueDate\": \"2021-11-29\",\n" +
                "            \"batchStockNumber\": 2\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.put("http://localhost:8090/api/v1/fresh-products/inboundorder/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad)
                        .header("Authorization", "Bearer " + auth().getToken()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldlistBatchStockByProductId() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/api/v1/fresh-products/inboundorder/list/RF"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
