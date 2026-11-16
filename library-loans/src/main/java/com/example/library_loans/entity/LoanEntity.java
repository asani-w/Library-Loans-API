package com.example.library_loans.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity 
public class LoanEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @NotNull (message="Book Id is Required")
    private Integer bookId;
    @NotNull 
    private Integer borrowerId;

    @CreationTimestamp 
    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateBorrowed;
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateDue;

     public enum LoanStatus{
        Pending_Return,
        Returned,
        Unreturned
    }
    @NotNull 
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;


    public LoanEntity(){}

    public Integer getId(){ return id;}
    public Integer getBookId(){return bookId;}
    public void setBookId(Integer bookId){this.bookId=bookId;}
    public Integer getBorrowerId(){return borrowerId;}
    public void setBorrowerId(Integer borrowerId){this.borrowerId=borrowerId;}
    public LocalDateTime getDateBorrowed(){return dateBorrowed;}
    public LocalDateTime getDateDue(){return dateDue;}
    public void setDateDue(LocalDateTime dateDue){this.dateDue=dateDue;}
    public LoanStatus getLoanStatus() {return loanStatus;}
    public void setLoanStatus(LoanStatus loanStatus){this.loanStatus=loanStatus;}

    
        
    }



    

