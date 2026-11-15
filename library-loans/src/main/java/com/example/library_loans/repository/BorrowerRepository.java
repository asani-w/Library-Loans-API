package com.example.library_loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BrorrowerEntity;

public interface BorrowerRepository extends JpaRepository<BrorrowerEntity, Integer> {

    
}
