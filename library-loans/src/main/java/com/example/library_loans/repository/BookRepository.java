package com.example.library_loans.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity>findByTitle(String title);
    List<BookEntity>findByCategory(String category);
    List<BookEntity>findBySource(String source);
    List<BookEntity>findByDateAdded(LocalDateTime dateAdded);

    
}
