package com.example.library_loans.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.service.BookService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping ("/api/books")
@RestController 
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/")
    public List<BookEntity> getAllBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Optional<BookEntity> getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }
    
    @PostMapping
    public BookEntity create(@RequestBody BookEntity book) {
        return bookService.createBook(book);
    }
    
    
}
