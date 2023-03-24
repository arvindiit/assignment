package com.arvind.assignment.controller;

import com.arvind.assignment.dto.AccountDTO;
import com.arvind.assignment.service.AccountService;
import com.arvind.assignment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;
    
    @GetMapping("/account")
    public String getAccount(@ModelAttribute("accountDto") @Validated AccountDTO accountDTO, Model model) {
        model.addAttribute("account", accountService.getAccount(accountDTO.getAccountId()));
       return ControllerHelper.setPage(model);
    }
    @PostMapping("/account/save")
    public String createAccount(@ModelAttribute("newAccount") @Validated AccountDTO accountDTO, Model model) {
        
        model.addAttribute("savedAccount", accountService.saveAccount(accountDTO));
        return ControllerHelper.setPage(model);
    }
    
    @PostMapping("/account/transact")
    public String doTransaction(@ModelAttribute("transact") @Validated AccountDTO accountDTO, Model model) {
        model.addAttribute("savedTransaction", transactionService.doTransaction(accountDTO));
        return ControllerHelper.setPage(model);
    }
}
