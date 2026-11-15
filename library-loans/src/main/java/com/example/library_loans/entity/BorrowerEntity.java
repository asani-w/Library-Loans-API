package com.example.library_loans.entity;

import jakarta.persistence.*;
import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity 
public class BorrowerEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    private String firstName;
    private String lastName;
    
    @CreationTimestamp 
    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;

    public BorrowerEntity(){}

    public Integer getId(){return id;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public LocalDateTime getDateCreated(){return dateCreated;}
}