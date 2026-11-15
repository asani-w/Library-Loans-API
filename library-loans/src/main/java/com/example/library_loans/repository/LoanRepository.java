package com.example.library_loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.LoanEntity;


public interface LoanRepository extends JpaRepository<LoanEntity,Integer> {

    
    
}
