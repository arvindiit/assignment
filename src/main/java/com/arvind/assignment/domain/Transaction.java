package com.arvind.assignment.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private long amount;
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;
    
    @CreationTimestamp
    private LocalDateTime time;
    
    public enum TransactionType {
        DEBIT, CREDIT
    }
}
