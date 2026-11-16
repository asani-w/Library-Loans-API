package com.example.library_loans.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCategory;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
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
    public List<BookEntity> getBookByBookCategory(BookCategory bookcategory){
        List<BookGenre> bookGenres = new ArrayList<>();
        for (BookGenre genre : BookGenre.values()) {
            if (genre.getCategory()==bookcategory){
                bookGenres.add(genre);
            }
            
        }
        return bookRepository.findByBookGenreIn(bookGenres);
    }
    public List<BookEntity> getBookByBookGenre(BookGenre bookGenre){
        return bookRepository.findByBookGenre(bookGenre);
    }
    public List<BookEntity> getBookByDateAdded(LocalDateTime dateAdded){
        return bookRepository.findByDateAdded(dateAdded);
    }
    public List<BookEntity> getBookByBookSource(BookSource booksource){
        return bookRepository.findByBookSource(booksource);
    }

    public List<BookEntity> getBookByStatus(BookStatus bookStatus){
        return bookRepository.findByBookStatus(bookStatus);
    }
    public List<BookEntity> getBookByCondition(BookCondition bookCondition){
        return bookRepository.findByBookCondition(bookCondition);
    }

    public BookEntity updateBook(Integer id, BookEntity entity){
        Optional<BookEntity> book = getBookById(id);
        BookEntity record=book.get();
        record.setTitle(entity.getTitle());
        record.setBookGenre(entity.getBookGenre());
        record.setBookCondition(entity.getBookCondition());
        record.setBookStatus(entity.getBookStatus());
        record.setBookSource(entity.getBookSource());
        
        return bookRepository.save(record);

    }

    public BookEntity deleteBook(Integer id){
        Optional<BookEntity> book= getBookById(id);
        BookEntity record=book.get();
        bookRepository.delete(record);
        return record;
    }

}
