package com.example.library_loans.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    private String firstName;
    @NotNull 
    private String lastName;
    @NotNull 
    private String email;
    @NotNull 
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

}