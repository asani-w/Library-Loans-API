package com.example.library_loans.entity;

import jakarta.persistence.*;
import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity 
public class LoanEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    private Integer bookId;
    private Integer borrowerId;
    @CreationTimestamp 

    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateBorrowed;
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateDue;

    public LoanEntity(){}

    public Integer getId(){ return id;}
    public Integer getBookId(){return bookId;}
    public void setBookId(Integer bookId){this.bookId=bookId;}
    public Integer getborrowerId(){return borrowerId;}
    public void setBorrowerId(Integer borrowerId){this.borrowerId=borrowerId;}
    public LocalDateTime getdateBorrowed(){return dateBorrowed;}
    public LocalDateTime getDateDue(){return dateDue;}
    public void setDateDue(LocalDateTime dateDue){this.dateDue=dateDue;}



    
}
