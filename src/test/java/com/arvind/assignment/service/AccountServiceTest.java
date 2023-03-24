package com.arvind.assignment.service;

import com.arvind.assignment.domain.CurrentAccount;
import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.domain.Transaction;
import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    
    @InjectMocks
    private AccountService accountService;
    
    @Mock
    private CustomerService customerService;
    
    @Mock
    private AccountRepository accountRepository;
    
    @Test
    void testSaveAccount() {
        AccountDTO account = new AccountDTO();
        account.setBalance(1000);
        account.setCustomerId(1L);
        
        when(customerService.fetchCustomer(1L)).thenReturn(new Customer());
        when(accountRepository.save(any())).thenReturn(new CurrentAccount());
        CurrentAccount savedAccount = accountService.saveAccount(account);
        Set<Transaction> transactions = savedAccount.getTransactions();
        assertEquals(1,transactions.size());
        Transaction transaction = transactions.stream().findFirst().get();
        assertEquals(1000, transaction.getAmount());
        assertEquals(Transaction.TransactionType.CREDIT, transaction.getTransactionType());
    }
    
    @Test
    void testFetchAccount() {
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        account.setId(1L);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertEquals(1L,accountService.getAccount(1L).getId());
    }
}
