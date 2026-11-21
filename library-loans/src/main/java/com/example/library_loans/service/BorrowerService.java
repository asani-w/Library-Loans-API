package com.example.library_loans.service;

import com.example.library_loans.repository.BookRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.BorrowerEntity.MembershipStatus;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.exception.MissingInputException;
import com.example.library_loans.repository.BorrowerRepository;
import com.example.library_loans.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service 
public class BorrowerService {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final LoanRepository loanRepository;


    public BorrowerService(BorrowerRepository borrowerRepository, LoanRepository loanRepository, BookRepository bookRepository, BookService bookService){
        this.borrowerRepository=borrowerRepository;
        this.loanRepository=loanRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        
    }

    public List<BorrowerEntity> getBorrowers(){
        return borrowerRepository.findAll();
    }

    public Optional<BorrowerEntity> getBorrowerById(Integer id){
        return borrowerRepository.findById(id);
    }
    @Transactional 
    public String createBorrower(BorrowerEntity borrower){
        if(!(borrower.getFirstName().length()>=3) || !(borrower.getFirstName().length()<=30)){
            throw new BadInputException("First name is not between 2 and 30 characters");
        }
        if(!(borrower.getLastName().length()>=3) || !(borrower.getLastName().length()<=30)){
            throw new BadInputException("Last name is not between 2 and 30 characters");
        }
        if (!(borrower.getEmail().contains("@"))){
            throw new BadInputException("email does not contain @");
        }
        if (!(borrower.getEmail().endsWith(".com"))){
            throw new BadInputException("email does end with .com");
        }
        if (!(borrower.getEmail().length()>=5)&& !(borrower.getEmail().length()<=200)){
            throw new BadInputException("email is not between 5 and 200 characters");
        }
        
        if(borrower.getTelephoneNumber().contains("(")||borrower.getTelephoneNumber().contains(")")){
            throw new BadInputException("Telephone number contains ( )");
        }
        if(borrower.getTelephoneNumber().contains("+")){
            throw new BadInputException("Telephone number contains +");
        }
        if ((borrower.getTelephoneNumber().length() >10)){
            throw new BadInputException("Telephone number is greater 10 digits");
        }
        if ((borrower.getTelephoneNumber().length() <10)){
            throw new BadInputException("Telephone number is less than 10 digits");
        }
        if (!(borrower.getTelephoneNumber().chars().allMatch(Character::isDigit) )){
            throw new BadInputException("I dont think those are all digits...");
        }
        if (borrower.getMembershipStatus()==MembershipStatus.Expired){
            borrower.setExpiryDate(LocalDateTime.now());
        }
        else{
        borrower.setExpiryDate(LocalDateTime.now().plusMonths(6));
        }

        if((borrowerRepository.existsByFirstNameAndLastName(borrower.getFirstName(), borrower.getLastName())) ){
            if( borrowerRepository.existsByEmail(borrower.getEmail())){
                throw new BadInputException("Borrower with that Name and Email exists already");
            }
            else if(borrowerRepository.existsByTelephoneNumber(borrower.getTelephoneNumber())){
                throw new BadInputException("Borrower with that Name and Telephone Number already exists");
            }
        }

        borrowerRepository.save(borrower);
        return("Borrower was created successfully");
        
    }

    public List<BorrowerEntity> getBorrowerByFirstName(String fname){
        return borrowerRepository.findByFirstName(fname);
    }

    public List<BorrowerEntity> getBorrowerByLastName(String lname ){
        return borrowerRepository.findByLastName(lname);
    }

    public List<BorrowerEntity> getBorrowerByEmail(String email ){
        return borrowerRepository.findByEmail(email);
    }

    public List<BorrowerEntity> getBorrowerByTelephoneNumber(String tel){
        return borrowerRepository.findByTelephoneNumber(tel);
    }

    public List<BorrowerEntity> getBorrowerByDateTimeCreated(LocalDateTime date){
        return borrowerRepository.findByDateCreated(date);
    }

    public List<BorrowerEntity> getBorrowerByMembershipStatus(MembershipStatus membershipStatus){
        return borrowerRepository.findByMembershipStatus(membershipStatus);
    }
    public List<BorrowerEntity>getBorrowerByDateTimeExpired(LocalDateTime date){
        return borrowerRepository.findByExpiryDate(date);
    }
    public List<BorrowerEntity> getBorrowerByDateCreated(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return borrowerRepository.findByDateCreatedGreaterThanEqualAndDateCreatedLessThan(start, end);
    }

    public List<BorrowerEntity> getBorrowerByDateExpired(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return borrowerRepository.findByExpiryDateGreaterThanEqualAndExpiryDateLessThan(start, end);
    }

    public String update(Integer id, BorrowerEntity entity){
        Optional<BorrowerEntity> borrower = getBorrowerById(id);
        BorrowerEntity record = borrower.get();

        if(!(entity.getFirstName().length()>=3) && !(entity.getFirstName().length()<=30)){
            throw new BadInputException("First name is not between 2 and 30 characters");
        }
        if(!(entity.getLastName().length()>=3) && !(entity.getLastName().length()<=30)){
            throw new BadInputException("First name is not between 2 and 30 characters");
        }
        if (!(entity.getEmail().contains("@"))){
            throw new BadInputException("email does not contain @");
        }
        if (!(entity.getEmail().length()>=5)&& !(entity.getEmail().length()<=200)){
            throw new BadInputException("email is not beteen 5 and 200 characters");
        }
        if (!(entity.getTelephoneNumber().length() ==10)){
            throw new BadInputException("telephone number is greater than or less than 10 characters");
        }
        if(entity.getTelephoneNumber().contains("(")||entity.getTelephoneNumber().contains(")")){
            throw new BadInputException("Telephone number contains ( )");
        }
        if(entity.getTelephoneNumber().contains("+")){
            throw new BadInputException("Telephone number contains +");
        }

        if (entity.getMembershipStatus()==MembershipStatus.Expired){
            entity.setExpiryDate(LocalDateTime.now());
        }

        record.setFirstName(entity.getFirstName());
        record.setLastName(entity.getLastName());
        record.setEmail(entity.getEmail());
        record.setTelephoneNumber(entity.getTelephoneNumber());
        record.setMembershipStatus(entity.getMembershipStatus());
        borrowerRepository.save(record);
        return ("Borrower has been updated Sucessfully");
    }

    @Transactional 
    public String suspend(Integer id){
        Optional<BorrowerEntity> borrower= getBorrowerById(id);
        if (borrower.isEmpty()){
            throw new MissingInputException("Borrower does not exist");
        }
        BorrowerEntity record=borrower.get();
        record.setMembershipStatus(MembershipStatus.Suspended);
        return ("Borrower has been suspended");
    }

    @Transactional 
    public String delete(Integer id){
        Optional<BorrowerEntity> borrower= getBorrowerById(id);
        if (borrower.isEmpty()){
            throw new MissingInputException("Borrower does not exist");
        }
        BorrowerEntity record=borrower.get();
        List<LoanEntity> loans = loanRepository.findByBorrowerId(record.getId());
        if (!(loans.isEmpty())){
            throw new BadInputException("A loan already exists for this book");
        }
        borrowerRepository.delete(record);
        return ("Borrower deleted");
    }

    @Transactional 
    public String strictDelete(Integer id){
        Optional<BorrowerEntity> borrower= getBorrowerById(id);
        if (borrower.isEmpty()){
            throw new MissingInputException("Borrower/Member does not exist");
        }
        BorrowerEntity record=borrower.get();
        
        List<LoanEntity> loans = loanRepository.findByBorrowerId(record.getId());
        if(loans.isEmpty()){
            throw new MissingInputException("No loans associated found");
        }
        for (LoanEntity loan : loans) {
            if (loan.getBorrowerId().equals(record.getId())){
                

                Optional<BookEntity> book = bookService.getBookById(loan.getBookId());
                if (book.isEmpty()){
                    throw new MissingInputException("Book not found");
                }
                BookEntity bookRecord = book.get();
                bookRecord.setBookStatus(BookStatus.Available);
                bookRepository.save(bookRecord);
                loanRepository.delete(loan);
            }
            else{
                throw new MissingInputException("No loans associated with this Borrower is found, use delete instead of strict delete");
            }
        }
        borrowerRepository.delete(record);
        return ("Borrowers and loans associated were deleted");
    }
    public String bulkCreate(List<BorrowerEntity> borrowers){
        int errorCount=0;
        int totalEntries=0;
        String toSend="";
        for (BorrowerEntity borrower : borrowers) {
            String response = createBorrower(borrower); 
            totalEntries+=1;
            if(response!="Borrower was created successfully"){
                errorCount+=1;
            }  
        }

        if((errorCount==0) && (totalEntries!=0)){
            toSend=("All "+totalEntries+" Borrowers have been added");
        }
        else if(totalEntries==0){
            toSend=("No Entries have been detected");
        }
        else{
            toSend=("Only "+ Integer.toString(errorCount-totalEntries) +" borrowers have been saved out of " + totalEntries + " total borrowers");
        }
        return (toSend);
    }
}
