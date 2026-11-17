package com.example.library_loans.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity 
public class BorrowerEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @NotNull 
    @Size (min=2,max=30)
    private String firstName;
    @NotNull 
    @Size (min=2,max=30)
    private String lastName;
    @NotNull 
    @Size (min=5,max=200)
    private String email;
    @NotNull 
    @Size (min=10,max=10)
    private String telephoneNumber;

    @CreationTimestamp 
    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;

    public enum MembershipStatus {
        Active,
        Expired,
        Suspended
    }

    @Enumerated(EnumType.STRING)
    @NotNull 
    private MembershipStatus membershipStatus;

    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime expiryDate;

    public BorrowerEntity(){}

    public Integer getId(){return id;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public LocalDateTime getDateCreated(){return dateCreated;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getTelephoneNumber(){return telephoneNumber;}
    public void setTelephoneNumber(String telephoneNumber){this.telephoneNumber=telephoneNumber;}
    public MembershipStatus getMembershipStatus(){return membershipStatus;}
    public void setMembershipStatus(MembershipStatus membershipStatus){this.membershipStatus=membershipStatus;}
    public LocalDateTime getExpiryDate(){return expiryDate;}
    public void setExpiryDate(LocalDateTime expiryDate){this.expiryDate=expiryDate;}

}