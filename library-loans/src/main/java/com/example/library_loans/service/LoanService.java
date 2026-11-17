package com.example.library_loans.service;




import com.example.library_loans.exception.GlobalExceptionHandler;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.library_loans.repository.LoanRepository;
import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.BorrowerEntity.MembershipStatus;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.exception.MissingInputException;


import jakarta.transaction.Transactional;

@Service 
public class LoanService {

    private final GlobalExceptionHandler globalExceptionHandler;
    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final BorrowerService borrowerService;


    public LoanService(LoanRepository loanRepository, BookService bookService, BorrowerService borrowerService, GlobalExceptionHandler globalExceptionHandler){
        this.loanRepository=loanRepository;
        this.bookService=bookService;
        this.borrowerService=borrowerService;
        this.globalExceptionHandler = globalExceptionHandler;
    }


    public List<LoanEntity> getLoans(){
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> getLoanById(Integer id){ 
        return loanRepository.findById(id);
    }

    @Transactional 
    public String createLoan(LoanEntity loan){
        Integer bookId= loan.getBookId();
        Integer borrowerId=loan.getBorrowerId();

        Optional<BookEntity> book =  bookService.getBookById(bookId);
        Optional<BorrowerEntity> borrower = borrowerService.getBorrowerById(borrowerId);
        if (book.isEmpty()){
            throw new MissingInputException("Book not Found");
        }
        if (borrower.isEmpty()){
            throw new MissingInputException("Borrower not Found");
        }
        BookEntity bookRecord = book.get();
        BorrowerEntity borrowerRecord = borrower.get();



        //book stuff
        if (bookRecord.getBookStatus() == BookStatus.Available & bookRecord.getBookCondition()!=BookCondition.Poor){
            bookRecord.setBookStatus(BookStatus.Borrowed);
        }
        else if (bookRecord.getBookStatus()==BookStatus.Borrowed){
            throw new BadInputException("Book Is already on a loan");
        }
        else if (bookRecord.getBookStatus()==BookStatus.Removed){
            throw new BadInputException("Book is Removed from the Catalog");
        }
        else if(bookRecord.getBookStatus()==BookStatus.Reserved){
            throw new BadInputException("Book has been reserved, Cannot be loaned");
        }
        else{
            throw new BadInputException("Books of Poor Condition cannot be loaned");
        }

        //borrowerStuff
        if(borrowerRecord.getMembershipStatus() == MembershipStatus.Expired){
            throw new BadInputException("Borrower's Memebership Has been Expired");
        }
        else if(borrowerRecord.getMembershipStatus()==MembershipStatus.Suspended){
            throw new BadInputException("Borrower's Membership is suspeneded due to a past offence");
        }

        loan.setDateDue(LocalDateTime.now().plusDays(7));
        loan.setLoanStatus(LoanStatus.Pending_Return);
        bookService.updateBook(bookId, bookRecord);
        loanRepository.save(loan); 
        return ("Loan has been created successfully");
    }

    public List<LoanEntity> getLoanByBookId(Integer bookId){
        return loanRepository.findByBookId (bookId);
    }

    public List<LoanEntity> getLoanByBorrowerId(Integer borrowerId){
        return loanRepository.findByBorrowerId(borrowerId);
    }

    public List<LoanEntity> getLoanByDateTimeBorrowed(LocalDateTime dateBorrowed){
        return loanRepository.findByDateBorrowed(dateBorrowed);
    }
    public List<LoanEntity> getLoanByDateTimeDue(LocalDateTime dateDue){
        return loanRepository.findByDateDue(dateDue);
    }

    public List<LoanEntity> getLoanByLoanStatus(LoanStatus loanStatus){
        return loanRepository.findByLoanStatus(loanStatus);
    }

    public List<LoanEntity> getLoanByDateTimeReturned(LocalDateTime dateReturned){
        return loanRepository.findByDateReturned(dateReturned);
    }
    public List<LoanEntity> getLoanByDateBorrowed(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return loanRepository.findByDateBorrowedGreaterThanEqualAndDateBorrowedLessThan(start,end);
    }
    public List<LoanEntity> getLoanByDateDue(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return loanRepository.findByDateDueGreaterThanEqualAndDateDueLessThan(start,end);
    }
    public List<LoanEntity> getLoanByDateReturned(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return loanRepository.findByDateReturnedGreaterThanEqualAndDateReturnedLessThan(start, end);
    }
    @Transactional 
    public String update(Integer id, LoanEntity entity){

        Optional<LoanEntity> loan = getLoanById(id);
        Optional<BookEntity> book = bookService.getBookById(entity.getBookId());
        Optional<BorrowerEntity> borrower = borrowerService.getBorrowerById(entity.getBorrowerId());
        if (loan.isEmpty()){
            throw new MissingInputException("Loan Not Found");
        }
        if(book.isEmpty()){
            throw new MissingInputException("Book Not Found");
        }
        if(borrower.isEmpty()){
            throw new MissingInputException("Borrower Not Found");
        }

        LoanEntity record=loan.get();
        BookEntity bookRecord=book.get();
        BorrowerEntity borrowerRecord=borrower.get();


        if (entity.getLoanStatus()==LoanStatus.Returned){
            record.setLoanStatus(entity.getLoanStatus());
            bookRecord.setBookStatus(BookStatus.Available);
        }

        if(record.getDateDue().isAfter(LocalDateTime.now())){
            record.setLoanStatus(LoanStatus.Unreturned);
        }
        if (borrowerRecord.getExpiryDate().isAfter(LocalDateTime.now())){
            borrowerRecord.setMembershipStatus(MembershipStatus.Expired);
        }

        if(borrowerRecord.getMembershipStatus()==MembershipStatus.Expired){
            throw new BadInputException("Membership is Expired, cannot use Library services");
        }
        if(borrowerRecord.getMembershipStatus()==MembershipStatus.Suspended){
            throw new BadInputException("Membership is Suspended, cannot use Library Services");
        }

        bookService.updateBook(bookRecord.getId(), bookRecord);
        loanRepository.save(record);
        return ("Loan has been updated Sucessfully");
    }

     @Transactional 
    public String unreturn(Integer id){
        Optional<LoanEntity> loan= getLoanById(id);
        Optional<BorrowerEntity> borrower= borrowerService.getBorrowerById(id);

        if (loan.isEmpty()){
            throw new MissingInputException("loan does not exist");
        }
        if (borrower.isEmpty()){
            throw new MissingInputException("borroawe associated does not exist");
        }
        BorrowerEntity borrowerRecord=borrower.get();
        LoanEntity record=loan.get();
        record.setLoanStatus(LoanStatus.Unreturned);
        borrowerService.suspend(borrowerRecord.getId());
        loanRepository.save(record);
        return ("Borrower has been suspended");
    }

    public String delete(Integer id){
        Optional<LoanEntity> loan = getLoanById(id);
        if(loan.isEmpty()){
            throw new MissingInputException("Loan Not Found");
        }
        LoanEntity record=loan.get();
        loanRepository.delete(record);
        return ("Loan Deleted successfully");
        
    }

}

