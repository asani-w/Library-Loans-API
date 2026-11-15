package com.example.library_loans.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BookEntity;

import com.example.library_loans.repository.BookRepository;

@Service 
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(Integer id){
        return bookRepository.findById(id);
    }

    public BookEntity createBook(BookEntity book){
        return bookRepository.save(book);
    }

    public List<BookEntity> getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    public List<BookEntity> getBookByCategory(String category){
        return bookRepository.findByCategory(category);
    }
    public List<BookEntity> getBookByDateAdded(LocalDateTime dateAdded){
        return bookRepository.findByDateAdded(dateAdded);
    }
    public List<BookEntity> getBookBySource(String source){
        return bookRepository.findBySource(source);
    }
}
