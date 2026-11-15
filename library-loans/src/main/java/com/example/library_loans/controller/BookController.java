package com.example.library_loans.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.service.BookService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @GetMapping("/title/{title}")
    public List<BookEntity> getBookByTitle(@PathVariable String title){
        return bookService.getBookByTitle(title);
    }
    /*
    @GetMapping("/category/{cat}")
    public List<BookEntity>getBookByCategory(@PathVariable String cat){
        return bookService.getBookByCategory(cat);
    }
    @GetMapping("/dateadded/{date}")
    public List<BookEntity> getBookByDateAdded(@PathVariable date){
        return bookService.getBookByDateAdded(date);
    }
    */
    @GetMapping("/source/{source}")
    public List<BookEntity> getBookBySource(@PathVariable String source){
        return bookService.getBookBySource(source);
    }

    @GetMapping("/BookStatus/{status}")
    public List<BookEntity> getBookByBookStatus(BookStatus bookStatus){
        return bookService.getBookByStatus(bookStatus);
    }

    @GetMapping("BookCondition/{condition}")
    public List<BookEntity> getBookByCondition(BookCondition bookCondition){
        return bookService.getBookByCondtion(bookCondition);
    }
    
    @PostMapping
    public BookEntity create(@RequestBody BookEntity book) {
        return bookService.createBook(book);
    }
    
    @DeleteMapping("/{id}")
    public BookEntity deleteBook(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }
}
