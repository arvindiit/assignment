package com.arvind.assignment.controller;

import com.arvind.assignment.domain.Customer;
import com.arvind.assignment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerService customerService;
   
    @GetMapping("/")
    public String index(Model model) {
        return ControllerHelper.setPage(model);
    }
    
    @GetMapping("/customer")
    public String searchCustomer(@ModelAttribute("customer") @Validated Customer customer, Model model) {
        model.addAttribute("searchedCustomer", customerService.fetchCustomer(customer.getId()));
        return ControllerHelper.setPage(model);
    }
    @PostMapping("/customer/save")
    public String createCustomer(@ModelAttribute("customer") @Validated Customer customer,
                                 BindingResult result, Model model) {
        model.addAttribute("savedCustomer", customerService.saveCustomer(customer));
        return ControllerHelper.setPage(model);
    }

}
