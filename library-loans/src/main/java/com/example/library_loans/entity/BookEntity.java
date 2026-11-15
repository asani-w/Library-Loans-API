package com.example.library_loans.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class BookEntity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String category;
    private LocalDateTime dateAdded;
    private String source;

    public BookEntity() {}

    public Integer getId() {return id;}
    public String getTitle(){return title;}
    public void  setTitle(String title){ this.title=title;}
    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}
    public LocalDateTime getDateAdded(){return dateAdded;}
    public void setDateAdded(){dateAdded=LocalDateTime.now();}
    public String getSource(){return source;}
    public void setSource(String source){this.source=source;}

}