package com.arvind.assignment.controller;

import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerIntegrationTest extends AbstractIntegrationTest{
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @AfterEach
    public void tearDown(){
        customerRepository.deleteAll();
    }
    
    @Test
    void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("test-first-name");
        customer.setLastName("test-last-name");
        customer.setAge(30);
        mockMvc.perform(post("/customer/save")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("savedCustomer"));
    }
    
    @Test
    void testGetCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("test-first-name");
        customer.setLastName("test-last-name");
        customer.setAge(30);
        customerRepository.save(customer);
        mockMvc.perform(get("/customer")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("searchedCustomer"));
    }
    
    @Test
    void testGetCustomerNotFound() throws Exception {
        Customer customer = new Customer();
        customer.setId(100L);
        mockMvc.perform(get("/customer")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"));
    }
}
