package com.arvind.assignment.advice;

import com.arvind.assignment.controller.ControllerHelper;
import com.arvind.assignment.exception.InvalidAmountException;
import com.arvind.assignment.exception.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return ControllerHelper.setPage(model);
    }
    
    @ExceptionHandler(InvalidAmountException.class)
    public String handleInvalidAmountException(InvalidAmountException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return ControllerHelper.setPage(model);
    }
}
