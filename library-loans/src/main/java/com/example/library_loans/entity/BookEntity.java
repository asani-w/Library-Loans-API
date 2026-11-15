package com.example.library_loans.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class BookEntity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    private String title;
    private String category;
    @CreationTimestamp 
    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateAdded;
    private String source;

    public enum BookStatus{
        Borrowed,
        Available,
        Removed   
    }

    @Enumerated (EnumType.STRING)
    public BookStatus bookStatus;

    public enum BookCondition{
        New,
        Excellent,
        Acceptable,
        Poor
    }

    @Enumerated (EnumType.STRING)
    public BookCondition bookCondition;

    public BookEntity() {}

    public Integer getId() {return id;}
    public String getTitle(){return title;}
    public void  setTitle(String title){ this.title=title;}
    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}
    public LocalDateTime getDateAdded(){return dateAdded;}
    public String getSource(){return source;}
    public void setSource(String source){this.source=source;}
    public BookCondition getBookCondition(){return bookCondition;}
    public void setBookCondition(BookCondition bookCondition){this.bookCondition=bookCondition;}
    public BookStatus getBookStatus(){return bookStatus;}
    public void setBookStatus(BookStatus bookStatus){this.bookStatus=bookStatus;}
}

