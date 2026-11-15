package com.example.library_loans.controller;

import com.example.library_loans.service.LoanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController 

@RequestMapping ("/api/")
public class LoanController {
    
    private LoanService loanService;

    public  LoanController(LoanService loanService){
        this.loanService=loanService;
    }
    

    
}
