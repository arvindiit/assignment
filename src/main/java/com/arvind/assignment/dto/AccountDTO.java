package com.arvind.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    
    long customerId;
    long accountId;
    long balance;
    String debitCredit;
    
}
