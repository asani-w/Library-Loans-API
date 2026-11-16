package com.example.library_loans.service;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.repository.LoanRepository;
import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.service.BookService;

import jakarta.transaction.Transactional;

@Service 
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;


    public LoanService(LoanRepository loanRepository, BookService bookService){
        this.loanRepository=loanRepository;
        this.bookService=bookService;
    }


    public List<LoanEntity> getLoans(){
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> getLoanById(Integer id){ 
        return loanRepository.findById(id);
    }

    @Transactional 
    public LoanEntity createLoan(LoanEntity loan){
        loan.setDateDue(LocalDateTime.now().plusDays(7));
        Integer bookId= loan.getBookId();
        Optional<BookEntity> book =  bookService.getBookById(bookId);
        if (book.isEmpty()){return loan;}
        BookEntity bookRecord = book.get();
        if (bookRecord.getBookStatus() == BookStatus.Available){
            bookRecord.setBookStatus(BookStatus.Borrowed);
            bookService.updateBook(bookId, bookRecord);
            return loanRepository.save(loan);   
        }
        else
        {
            return loan;
        }
    }

    public List<LoanEntity> getLoanByBookId(Integer bookId){
        return loanRepository.findByBookId (bookId);
    }

    public List<LoanEntity> getLoanByBorrowerId(Integer borrowerId){
        return loanRepository.findByBorrowerId(borrowerId);
    }

    public List<LoanEntity> getLoanByDateBorrowed(LocalDateTime dateBorrowed){
        return loanRepository.findByDateBorrowed(dateBorrowed);
    }
    public List<LoanEntity> getLoanByDateDue(LocalDateTime dateDue){
        return loanRepository.findByDateDue(dateDue);
    }

    public List<LoanEntity> getLoanByLoanStatus(LoanStatus loanStatus){
        return loanRepository.findByLoanStatus(loanStatus);
    }

    public LoanEntity update(Integer id, LoanEntity entity){
        Optional<LoanEntity> loan = getLoanById(id);
        LoanEntity record=loan.get();
        record.setBorrowerId(entity.getBorrowerId());
        record.setLoanStatus(entity.getLoanStatus());
        return loanRepository.save(record);
    }


    public LoanEntity deleteLoan(Integer id){
        Optional<LoanEntity> loan = getLoanById(id);
        LoanEntity record=loan.get();
        loanRepository.delete(record);
        return record;
        

        
    }

}

