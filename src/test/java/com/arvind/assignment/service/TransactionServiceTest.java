package com.arvind.assignment.service;

import com.arvind.assignment.domain.CurrentAccount;
import com.arvind.assignment.domain.Transaction;
import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.exception.InvalidAmountException;
import com.arvind.assignment.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    
    @InjectMocks
    private TransactionService transactionService;
    
    @Mock
    private AccountService accountService;
    
    @Mock
    private TransactionRepository transactionRepository;
    
    @Test
    void testDoValidTransaction() {
        AccountDTO account = new AccountDTO();
        account.setBalance(500);
        account.setAccountId(1L);
        account.setDebitCredit("debit");
        
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(1L);
        currentAccount.setBalance(1000);
        when(accountService.getAccount(1L)).thenReturn(currentAccount);
        when(transactionRepository.save(any())).thenReturn(new Transaction());
        
        Transaction transaction = transactionService.doTransaction(account);
        assertEquals(500, currentAccount.getBalance());
        assertEquals(500, transaction.getAmount());
        assertEquals(Transaction.TransactionType.DEBIT, transaction.getTransactionType());
        
    }
    
    @Test
    void testDoInValidTransaction() {
        AccountDTO account = new AccountDTO();
        account.setBalance(1500);
        account.setAccountId(1L);
        account.setDebitCredit("debit");
        
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(1L);
        currentAccount.setBalance(1000);
        when(accountService.getAccount(1L)).thenReturn(currentAccount);
        Exception exception = assertThrows(InvalidAmountException.class, () -> transactionService.doTransaction(account));
        String expectedMessage = "Invalid amount. Current balance is lower than requested amount";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
