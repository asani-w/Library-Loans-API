package com.example.library_loans.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCategory;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.service.BookService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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

    
    @GetMapping("/dateadded/datetime/{dateTime}")
    public List<BookEntity> getBookByDateTimeAdded(@PathVariable LocalDateTime dateTime){
        return bookService.getBookByDateTimeAdded(dateTime);
    }
    @GetMapping("/dateadded/date/{date}")
    public List<BookEntity> getBookByDateAdded(@PathVariable String date){
        return bookService.getBookByDateAdded(date);
    }
    
    @GetMapping("/BookSource/{source}")
    public List<BookEntity> getBookBySource(@PathVariable BookSource source){
        return bookService.getBookByBookSource(source);
    }

    @GetMapping("/BookGenre/{genre}")
    public List<BookEntity>getBookByGenre(@PathVariable BookGenre genre){
        return bookService.getBookByBookGenre(genre);
    }

    @GetMapping("/BookCategory/{category}")
    public List<BookEntity>getBookByCategory(@PathVariable BookCategory category){
        return bookService.getBookByBookCategory(category);
    }

    @GetMapping("/BookStatus/{status}")
    public List<BookEntity> getBookByBookStatus(BookStatus status){
        return bookService.getBookByStatus(status);
    }

    @GetMapping("BookCondition/{condition}")
    public List<BookEntity> getBookByCondition(BookCondition condition){
        return bookService.getBookByCondition(condition);
    }

    @GetMapping("/BookAuthor/{author}")
    public List<BookEntity> getBookByBookAuthor(@PathVariable String author){
        return bookService.getBookByBookAuthor(author);
    }
    @PostMapping
    public ResponseEntity<BookEntity> create(@Valid @RequestBody BookEntity book) {
        BookEntity savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    
    @PutMapping("/{id}")
    public BookEntity UpdateBook(@Valid @PathVariable Integer id, @RequestBody BookEntity entity) {
        return bookService.updateBook(id, entity);
    }

    @DeleteMapping("/{id}")
    public BookEntity deleteBook(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }
    
    /*
    //bulk adding books
    @PostMapping ("/bulk")
    public ResponseEntity<List<BookEntity>>createBulk( @RequestBody List<BookEntity> books){
        List<BookEntity> booksSaved=bookService.bulkCreate(books);
        return ResponseEntity.status(HttpStatus.CREATED).body(booksSaved);

    }
     */
}
