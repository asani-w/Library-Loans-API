package com.example.library_loans.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BorrowerEntity;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Integer> {

    List<BorrowerEntity>findByFirstName(String firstName);
    List<BorrowerEntity>findByLastName(String lastName);
    List<BorrowerEntity>findByEmail(String email);
    List<BorrowerEntity>findByTelephoneNumber(String telephoneNumber);
    List<BorrowerEntity>findByDateCreated(LocalDateTime dateCreated);
}
