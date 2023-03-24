package com.arvind.assignment.service;

import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.exception.NotFoundException;
import com.arvind.assignment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer fetchCustomer(long id){
        Optional<Customer> optional = customerRepository.findById(id);
        if( optional.isEmpty() ){
            throw new NotFoundException("Customer not found with id "+id);
        }
        return optional.get();
    }
    
}
