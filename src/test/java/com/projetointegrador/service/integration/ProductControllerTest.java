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
public class ProductControllerTest {

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
                "    \"name\": \"Banana\",\n" +
                "    \"description\":\"Caixa de Banana\",\n" +
                "    \"typeId\": \"1\"\n" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8090/api/v1/product/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + auth().getToken())
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldInsertOrders() throws Exception {

        String payLoad = "{\n" +
                "    \"date\":\"2021-10-31\",\n" +
                "    \"buyerId\": 1,\n" +
                "    \"orderStatus\":{\n" +
                "        \"statusCode\":\"cart\"\n" +
                "    },\n" +
                "    \"products\":[\n" +
                "        {\n" +
                "            \"productId\": \"MLB-129\",\n" +
                "            \"quantity\": 6            \n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8090/api/v1/product/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + auth().getToken())
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shoulGetProductSeller() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/product/list")
                                .header("Authorization", "Bearer " + auth().getToken()))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shoulListOrdersByOrderId() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8090/api/v1/product/orders/1")
                                .header("Authorization", "Bearer " + auth().getToken()))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdate() throws Exception {

        String payLoad = "{\n" +
                "    \"products\":[\n" +
                "        {\n" +
                "            \"purchaseItemId\": 1,\n" +
                "            \"productId\": \"MLB-129\",\n" +
                "            \"quantity\": 1           \n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.put("http://localhost:8090/api/v1/product/orders/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer " + auth().getToken())
                                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
