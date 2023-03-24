package com.arvind.assignment.repository;

import com.arvind.assignment.domain.Customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Test-first-name");
        customer.setLastName("Test-last-name");
        customer.setAge(30);
        Customer savedCustomer = customerRepository.save(customer);
        assertNotNull(savedCustomer);
    }
    
    @Test
    void getCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Test-first-name");
        customer.setLastName("Test-last-name");
        customer.setAge(30);
        Customer savedCustomer = customerRepository.save(customer);
        Customer fetchedCustomer = customerRepository.findById(savedCustomer.getId()).get();
        assertEquals("Test-first-name", fetchedCustomer.getFirstName());
        assertEquals("Test-last-name", fetchedCustomer.getLastName());
        assertEquals(30, fetchedCustomer.getAge());
    }
    
    @Test
    void deleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Test-first-name");
        customer.setLastName("Test-last-name");
        customer.setAge(30);
        Customer savedCustomer = customerRepository.save(customer);
        customerRepository.deleteById(savedCustomer.getId());
        Optional<Customer> optionalCustomer = customerRepository.findById(savedCustomer.getId());
        assertFalse(optionalCustomer.isPresent());
    }
}