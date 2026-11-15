package com.example.library_loans.service;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.repository.LoanRepository;
import com.example.library_loans.repository.BorrowerRepository;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.repository.BookRepository;

@Service 
public class LoanService {

    private final LoanRepository loanRepository;
    private final BorrowerRepository borrowerRepository;
    private final  BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BorrowerRepository borrowerRepository, BookRepository bookRepository){
        this.loanRepository=loanRepository;
        this.borrowerRepository=borrowerRepository;
        this.bookRepository=bookRepository;
    }

    public Optional<LoanEntity> getLoanById(Integer id){ 
        return loanRepository.findById(id);
    }

    public LoanEntity createLoan(LoanEntity loan){
        return loanRepository.save(loan);   
    }

}

