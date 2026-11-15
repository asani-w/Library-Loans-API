package com.example.library_loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    

    
}
