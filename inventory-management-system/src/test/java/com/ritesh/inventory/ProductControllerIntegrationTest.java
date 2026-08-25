package com.ritesh.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnProducts() throws Exception {

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {

        mockMvc.perform(get("/products/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400ForInvalidProduct() throws Exception {

        String invalidProduct = """
            {
                "name": "",
                "description": "Test product",
                "price": -100,
                "quantity": -5,
                "category": ""
            }
            """;

        mockMvc.perform(post("/products")
                .contentType(APPLICATION_JSON)
                .content(invalidProduct))
                .andExpect(status().isBadRequest());
    }
}