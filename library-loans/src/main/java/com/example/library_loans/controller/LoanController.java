package com.example.library_loans.controller;

import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.service.LoanService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/api/Loans")
@RestController 
public class LoanController {
    
    private final LoanService loanService;

    public  LoanController(LoanService loanService){
        this.loanService=loanService;
    }

    @GetMapping("/")
    public List<LoanEntity> getAllLoans(){
        return loanService.getLoans();
    }
    
    @GetMapping("/{id}")
        public Optional<LoanEntity> getLoanById(Integer id){
            return loanService.getLoanById(id);
    }

    
}
