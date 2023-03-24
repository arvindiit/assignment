package com.arvind.assignment.controller;

import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.dto.AccountDTO;
import org.springframework.ui.Model;

public class ControllerHelper {
    
    public static String setPage(Model model) {
        if(model.getAttribute("newAccount") == null){
            model.addAttribute("newAccount", new AccountDTO());
        }
        if(model.getAttribute("newCustomer") == null) {
            model.addAttribute("newCustomer", new Customer());
        }
        if( model.getAttribute("customer") == null) {
            model.addAttribute("customer", new Customer());
        }
        if(model.getAttribute("searchedCustomer") == null) {
            model.addAttribute("searchedCustomer", new Customer());
        }
        if( model.getAttribute("transact") == null) {
            model.addAttribute("transact", new AccountDTO());
        }
        return "index";
    }
}
