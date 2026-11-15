package com.example.library_loans.controller;

import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.service.BookService;
import com.example.library_loans.service.LoanService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RequestMapping ("/api/Loans")
@RestController 
public class LoanController {
    
    private final BookService bookService;
    private final LoanService loanService;

    public  LoanController(LoanService loanService, BookService bookService){
        this.loanService=loanService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<LoanEntity> getAllLoans(){
        return loanService.getLoans();
    }
    
    @GetMapping("/{id}")
    public Optional<LoanEntity> getLoanById(@PathVariable Integer id){
        return loanService.getLoanById(id);
    }

    @GetMapping("/bookid/{id}")
    public List<LoanEntity> getLoanByBookId(@PathVariable Integer bookId ){
        return loanService.getLoanByBookId(bookId);
    }
    @GetMapping("/borrowerid/{id}")
    public List<LoanEntity>getLoanByBorrowerId(@PathVariable Integer borrowerId){
        return loanService.getLoanByBookId(borrowerId);
    }

    /*
    @GetMapping("/dateborrowed/{date}")
    public List<LoanEntity> getLoanByDateBorrowed(date){
        return loanService.getLoanByDateBorrwed(date);
    }

    @GetMapping("/datedue/{date}")
    public List<LoanEntity> getLoanByDateDue(date){
        return loanService.getLoanByDateDue(date);
    }
    */
    @PostMapping
    public LoanEntity create(@RequestBody LoanEntity loan){
        return loanService.createLoan(loan);
    }
    
    @DeleteMapping("/{id}")
    public LoanEntity deleteLoan(@PathVariable Integer id){
        return loanService.deleteLoan(id);
    }
    

}
