package com.example.library_loans.service;


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

    public BookEntity saveBorrower(BookEntity borrow){
        return bookRepository.save(borrow);
    }
}
