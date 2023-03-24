package com.arvind.assignment.repository;

import com.arvind.assignment.domain.CurrentAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setup() {
        accountRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void saveAccount() {
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        CurrentAccount savedAccount = accountRepository.save(account);
        assertNotNull(savedAccount);
    }
    
    @Test
    void getAccount() {
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        CurrentAccount savedAccount = accountRepository.save(currentAccount);
        CurrentAccount fetchedAccount = accountRepository.findById(savedAccount.getId()).get();
        assertEquals(1000, fetchedAccount.getBalance());
    }
    
    @Test
    void deleteAccount() {
        CurrentAccount account = new CurrentAccount();
        account.setBalance(1000);
        CurrentAccount savedAccount = accountRepository.save(account);
        accountRepository.deleteById(savedAccount.getId());
        Optional<CurrentAccount> optionalAccount = accountRepository.findById(savedAccount.getId());
        assertFalse(optionalAccount.isPresent());
    }
}