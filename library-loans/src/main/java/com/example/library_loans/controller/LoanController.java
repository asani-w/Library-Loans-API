package com.example.library_loans.controller;

import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.service.BookService;
import com.example.library_loans.service.LoanService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping ("/api/Loans")
@RestController 
public class LoanController {
    
    private final LoanService loanService;

    public  LoanController(LoanService loanService, BookService bookService){
        this.loanService=loanService;
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
    public List<LoanEntity> getLoanByBookId(@PathVariable Integer id ){
        return loanService.getLoanByBookId(id);
    }
    @GetMapping("/borrowerid/{id}")
    public List<LoanEntity>getLoanByBorrowerId(@PathVariable Integer id){
        return loanService.getLoanByBorrowerId(id);
    }

    
    @GetMapping("/dateReturned/dateTime/{dateTime}")
    public List<LoanEntity> getLoanByDateTimeReturned(@PathVariable LocalDateTime dateTime){
        return loanService.getLoanByDateTimeReturned(dateTime);
    }

    @GetMapping("/dateReturned/date/{date}")
    public List<LoanEntity> getLoanByDateTimeReturned(@PathVariable String date){
        return loanService.getLoanByDateReturned(date);
    }

    @GetMapping("/dateborrowed/dateTime/{dateTime}")
    public List<LoanEntity> getLoanByDateTimeBorrowed(@PathVariable LocalDateTime dateTime){
        return loanService.getLoanByDateTimeBorrowed(dateTime);
    }

    @GetMapping("/dateborrowed/date/{date}")
    public List<LoanEntity> getLoanByDateBorrowed(@PathVariable LocalDateTime date){
        return loanService.getLoanByDateTimeBorrowed(date);
    }

    @GetMapping("/datedue/dateTime/{dateTime}")
    public List<LoanEntity> getLoanByDateTimeDue(@PathVariable LocalDateTime dateTime){
        return loanService.getLoanByDateTimeDue(dateTime);
    }
    @GetMapping("/datedue/date/{date}")
    public List<LoanEntity> getLoanByDateDue(@PathVariable String date){
        return loanService.getLoanByDateDue(date);
    }
    

    @GetMapping("/LoanStatus/{status}")
    public List<LoanEntity> getLoanByMembership(@PathVariable LoanStatus loanStatus){
        return loanService.getLoanByLoanStatus(loanStatus);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody LoanEntity loan){
        String message =loanService.createLoan(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @PathVariable Integer id, @RequestBody LoanEntity entity) {
        String message = loanService.update(id,entity);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> unreturn(@PathVariable Integer id) {
        String message = loanService.unreturn(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Integer id){
        String message = loanService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    

}
