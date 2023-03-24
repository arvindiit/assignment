package com.arvind.assignment.repository;

import com.arvind.assignment.domain.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setup() {
        transactionRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
    }

    @Test
    void saveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100);
        transaction.setTransactionType(Transaction.TransactionType.CREDIT);
        Transaction savedTransaction = transactionRepository.save(transaction);
        assertNotNull(savedTransaction);
    }
    
    @Test
    void getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100);
        transaction.setTransactionType(Transaction.TransactionType.CREDIT);
        Transaction savedTransaction = transactionRepository.save(transaction);
        Transaction fetchedTransaction = transactionRepository.findById(savedTransaction.getId()).get();
        assertEquals(100, fetchedTransaction.getAmount());
        assertEquals(Transaction.TransactionType.CREDIT, fetchedTransaction.getTransactionType());
    }
    
    @Test
    void deleteTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(100);
        Transaction savedTransaction = transactionRepository.save(transaction);
        transactionRepository.deleteById(savedTransaction.getId());
        Optional<Transaction> optional = transactionRepository.findById(savedTransaction.getId());
        assertFalse(optional.isPresent());
    }
}