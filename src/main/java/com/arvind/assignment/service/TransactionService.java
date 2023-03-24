package com.arvind.assignment.service;

import com.arvind.assignment.domain.CurrentAccount;
import com.arvind.assignment.domain.Transaction;
import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.exception.InvalidAmountException;
import com.arvind.assignment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    
    public Transaction doTransaction(AccountDTO accountDTO) {
        
        CurrentAccount account = accountService.getAccount(accountDTO.getAccountId());
        Transaction transaction = new Transaction();
        transaction.setAmount(accountDTO.getBalance());
        if(accountDTO.getDebitCredit().equals(Transaction.TransactionType.CREDIT.name())){
            transaction.setTransactionType(Transaction.TransactionType.CREDIT);
            account.setBalance(account.getBalance()+accountDTO.getBalance());
        } else {
            if(account.getBalance() < accountDTO.getBalance()) {
                throw new InvalidAmountException("Invalid amount. Current balance is lower than requested amount");
            } else {
                transaction.setTransactionType(Transaction.TransactionType.DEBIT);
                account.setBalance(account.getBalance()-accountDTO.getBalance());
            }
        }
        if(account.getTransactions() == null){
            account.setTransactions(new HashSet<>());
        }
        account.getTransactions().add(transaction);
        transactionRepository.save(transaction);
        return transaction;
    }
}
