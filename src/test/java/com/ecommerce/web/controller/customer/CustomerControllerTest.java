package com.ecommerce.web.controller.customer;

import com.ecommerce.data.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        mapper = new ObjectMapper();
    }

    @Test
    void testThatWeCanCallTheCreateCustomerEndpoint_thenReturnOk() throws Exception{
        customer.setFirstName("Jon");
        customer.setLastName("Snow");
        customer.setContact("0908877644");
        customer.setEmail("jon@gmail.com");
        customer.setPassword("jon123");

        this.mockMvc.perform(post("/customer/create")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(customer)))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
    }

    @Test
    void testFindAllCustomerEndPoint_thenReturnCustomerObject() throws Exception {
        this.mockMvc.perform(get("/customer/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testFindACustomerByIdEndPoint_thenReturnCustomerObject() throws Exception {
        this.mockMvc.perform(get("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenICallTheDeleteCustomer_thenReturnCustomerObject() throws Exception{

        this.mockMvc.perform(delete("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}