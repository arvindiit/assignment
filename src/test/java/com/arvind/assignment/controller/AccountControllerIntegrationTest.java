package com.arvind.assignment.controller;

import com.arvind.assignment.domain.CurrentAccount;
import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.repository.AccountRepository;
import com.arvind.assignment.repository.CustomerRepository;
import com.arvind.assignment.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountControllerIntegrationTest extends AbstractIntegrationTest{
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    CustomerService customerService;
    
    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("test-first-name");
        customer.setLastName("test-last-name");
        customer.setAge(30);
        return customer;
    }
    
    @AfterEach
    void tearEach(){
        customerRepository.deleteAll();
        accountRepository.deleteAll();
    }
    
    @Test
    void testSaveAccount() throws Exception {
        Customer customer = customerRepository.save(createCustomer());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(customer.getId());
        accountDTO.setBalance(1000);
        mockMvc.perform(post("/account/save")
                .flashAttr("newAccount", accountDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("savedAccount"));
    }
    
    @Test
    void testGetAccount() throws Exception {
        customerRepository.save(createCustomer());
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        accountRepository.save(account);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getId());
        mockMvc.perform(get("/account")
                .flashAttr("accountDto", accountDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("account"));
    }
    
    @Test
    void testGetAccountNotFound() throws Exception {
        customerRepository.save(createCustomer());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(100L);
        accountDTO.setBalance(1000);
        mockMvc.perform(post("/account/save")
                .flashAttr("accountDto", accountDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"));
    }
    
    @Test
    void testValidTransaction() throws Exception {
        customerRepository.save(createCustomer());
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        accountRepository.save(account);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getId());
        accountDTO.setBalance(500);
        accountDTO.setDebitCredit("debit");
        mockMvc.perform(post("/account/transact")
                .flashAttr("transact", accountDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("savedTransaction"));
    }
    
    @Test
    void testInValidTransaction() throws Exception {
        customerRepository.save(createCustomer());
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        accountRepository.save(account);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getId());
        accountDTO.setBalance(1500);
        accountDTO.setDebitCredit("debit");
        mockMvc.perform(post("/account/transact")
                .flashAttr("transact", accountDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"));
    }
}
