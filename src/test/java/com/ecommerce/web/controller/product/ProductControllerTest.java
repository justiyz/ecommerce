package com.ecommerce.web.controller.product;

import com.ecommerce.data.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        mapper = new ObjectMapper();
    }

    @Test
    void testThatWeCanCallTheGetAllProductsEndpoint() throws Exception {
        this.mockMvc.perform(get("/product/all"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    void testThatWeCanCallTheCreateProductEndpoint() throws Exception {
        product.setName("Hennessey");
        product.setPrice(40000.0);
        product.setQuantity(1);
        product.setExpDate("08-04-2030");
        product.setDescription("the finest of cognacs");

        this.mockMvc.perform(post("/product/create")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(product)))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
    }

    @Test
    void testThatWeCanCallTheUpdateProductEndpoint() throws Exception {

        product.setName("Sweet Potato");
        product.setPrice(3000.0);
        product.setQuantity(200);
        product.setExpDate("08-04-2031");
        product.setDescription("The very best");
        product.setId(1);

        this.mockMvc.perform(post("/product/update")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(product)))
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    void  testThatWeCanCallTheFindProductByIdEndpoint() throws Exception {
        this.mockMvc.perform(get("/product/find/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    void testThatWeCanCallTheDeleteByIdProductEndpoint() throws Exception {
        this.mockMvc.perform(delete("/product/delete/2"))
                    .andExpect(status().isOk())
                    .andReturn();


    }





}














