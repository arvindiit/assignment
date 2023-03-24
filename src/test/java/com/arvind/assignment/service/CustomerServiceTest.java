package com.arvind.assignment.service;

import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.exception.NotFoundException;
import com.arvind.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    
    @InjectMocks
    private CustomerService customerService;
    
    @Mock
    private CustomerRepository customerRepository;
    
    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Test-first-name");
        customer.setLastName("Test-last-name");
        customer.setAge(30);
        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        assertEquals(1L,customerService.saveCustomer(customer).getId());
    }
    
    @Test
    void testFetchCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Test-first-name");
        customer.setLastName("Test-last-name");
        customer.setAge(30);
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        assertEquals(1L,customerService.fetchCustomer(1L).getId());
    }
}
