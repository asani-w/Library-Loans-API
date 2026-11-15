package com.example.library_loans.service;



import java.time.LocalDateTime;
import java.util.List;
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


    public List<LoanEntity> getLoans(){
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> getLoanById(Integer id){ 
        return loanRepository.findById(id);
    }

    public LoanEntity createLoan(LoanEntity loan){
        loan.setDateDue(LocalDateTime.now().plusDays(7));
        return loanRepository.save(loan);   
    }

    public List<LoanEntity> getLoanByBookId(Integer bookId){
        return loanRepository.findByBookId (bookId);
    }

    public List<LoanEntity> getLoanByBorrowerId(Integer borrowerId){
        return loanRepository.findByBorrowerId(borrowerId);
    }

    public List<LoanEntity> getLoanByDateBorrwed(LocalDateTime dateBorrowed){
        return loanRepository.findByDateBorrowed(dateBorrowed);
    }
    public List<LoanEntity> getLoanByDateDue(LocalDateTime dateDue){
        return loanRepository.findByDateDue(dateDue);
    }

    public LoanEntity deleteLoan(Integer id){
        Optional<LoanEntity> loan = getLoanById(id);
        LoanEntity record=loan.get();
        loanRepository.delete(record);
        return record;
        

        
    }

}

