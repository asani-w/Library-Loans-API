package com.example.library_loans.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity 
public class LoanEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    private Integer borrowerId;
    private LocalDateTime dateBorrowed;
    private LocalDateTime dateDue;

    public LoanEntity(){}

    public Integer getId(){ return id;}
    public Integer getBookId(){return bookId;}
    public void setBookId(Integer bookId){this.bookId=bookId;}
    public Integer getborrowerId(){return borrowerId;}
    public void setBorrowerId(Integer borrowerId){this.borrowerId=borrowerId;}
    public LocalDateTime getdateBorred(){return dateBorrowed;}
    public void setDateBorrowed(){dateBorrowed=LocalDateTime.now();}
    public LocalDateTime getDateDue(){return dateDue;}
    public void setDateDue(int days){dateDue = (dateBorrowed.plusDays(days));}



    
}
