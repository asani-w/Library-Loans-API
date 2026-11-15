package com.example.library_loans.entity;

import jakarta.persistence.*;
import java.time.*;


@Entity 
public class BrorrowerEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String LastName;
    private LocalDateTime dateCreated;

    public BrorrowerEntity(){}

    public Integer getId(){return id;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}
    public String getLastName(){return LastName;}
    public void setLastName(String lastName){this.LastName=lastName;}
    public LocalDateTime getDateCreated(){return dateCreated;}
    public void setDateCreated(){dateCreated=LocalDateTime.now();}
}