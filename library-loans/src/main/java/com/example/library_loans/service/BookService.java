package com.example.library_loans.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.BookEntity.BookCategory;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.exception.MissingInputException;
import com.example.library_loans.repository.BookRepository;
import com.example.library_loans.service.LoanService;

import jakarta.transaction.Transactional;

@Service 
public class BookService {

    private final BookRepository bookRepository;
    private final LoanService loanService;

    public BookService(BookRepository bookRepository, LoanService loanService){
        this.bookRepository=bookRepository;
        this.loanService=loanService;
    }
    
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(Integer id){
        return bookRepository.findById(id);
    }

    @Transactional 
    public BookEntity createBook(BookEntity book){
        if(getBookByTitle(book.getTitle())!=null & getBookByBookAuthor(book.getAuthor())!=null){
            throw new BadInputException("Book With the Same Title and Same Author Already Exists");
        }
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
    public List<BookEntity> getBookByBookAuthor(String bookauthor){
        return bookRepository.findByBookAuthor(bookauthor);
    }

    @Transactional 
    public BookEntity updateBook(Integer id, BookEntity entity){
        Optional<BookEntity> book = getBookById(id);
        BookEntity record=book.get();
        
        List<LoanEntity> loan = loanService.getLoanByBookId(entity.getId());

        for (LoanEntity item : loan) {
            if(item.getLoanStatus()!=LoanStatus.Returned){
                throw new BadInputException("Book is already on a Loan and cannot be updated unless returned");
            }
            else if (item.getLoanStatus()==LoanStatus.Returned){
                record.setBookStatus(entity.getBookStatus());
            }
            else{
                throw new MissingInputException("Lon Not Found");
            }
        }
        //if we update book or author we need to check to see if it clashes with an existing record
        

        record.setTitle(entity.getTitle());
        record.setBookGenre(entity.getBookGenre());
        record.setBookCondition(entity.getBookCondition());
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
