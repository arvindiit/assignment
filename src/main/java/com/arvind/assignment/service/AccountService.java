package com.arvind.assignment.service;

import com.arvind.assignment.domain.CurrentAccount;
import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.domain.Transaction;
import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.exception.InvalidAmountException;
import com.arvind.assignment.exception.NotFoundException;
import com.arvind.assignment.repository.AccountRepository;
import com.arvind.assignment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    
    public CurrentAccount getAccount(long id) {
        Optional<CurrentAccount> optional = accountRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Account not found with id: "+id);
        }
        
        return optional.get();
    }
    
    public CurrentAccount saveAccount(AccountDTO accountDTO) {
        
        Customer customer = customerService.fetchCustomer(accountDTO.getCustomerId());
        if(customer.getAccounts() == null) {
            customer.setAccounts(new HashSet<>());
        }
        
        CurrentAccount account = new CurrentAccount();
        account.setBalance(accountDTO.getBalance());
        customer.getAccounts().add(account);
        Transaction transaction  = new Transaction();
        transaction.setTransactionType(Transaction.TransactionType.CREDIT);
        transaction.setAmount(account.getBalance());
        account.setTransactions(new HashSet<>());
        account.getTransactions().add(transaction);
        accountRepository.save(account);
        return account;
    }
}
