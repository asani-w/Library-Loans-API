package com.example.library_loans.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity>findByTitle(String title);
    List<BookEntity>findByBookGenreIn(List<BookGenre> bookGenre);
    List<BookEntity>findByBookGenre(BookGenre bookGenre);
    List<BookEntity>findByBookSource(BookSource bookSource);
    List<BookEntity>findByDateAdded(LocalDateTime dateAdded);
    List<BookEntity>findByBookCondition(BookCondition bookCondition);
    List<BookEntity>findByBookStatus(BookStatus bookStatus);
    
}
