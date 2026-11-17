package com.example.library_loans.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCategory;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.exception.MissingInputException;
import com.example.library_loans.repository.BookRepository;
import com.example.library_loans.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service 
public class BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookService(BookRepository bookRepository, LoanRepository loanRepository){
        this.bookRepository=bookRepository;
        this.loanRepository=loanRepository;
    }
    
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(Integer id){
        return bookRepository.findById(id);
    }
    @Transactional 
    public String createBook(BookEntity book){
        
        
        if(!(book.getTitle().length() <=3) || !(book.getTitle().length()>=500)){
            throw new BadInputException("Book Title is less than 3 characters or greater than 500 characaters");
        }
        if(!(book.getAuthor().length() <=3) || !(book.getAuthor().length()>=80)){
            throw new BadInputException("Book Author is less than 3 characters or greater than 80 characaters");
        }
        if(bookRepository.existsByTitleAndBookAuthor(book.getTitle(), book.getAuthor())){
            throw new BadInputException("Book With the Same Title and Same Author Already Exists");
        }
        /* 
        Commented out because Json auto sends its own error before its even passed so this doesnt even get read
        
        int validBookGenreCount=0;
        int validBookSourceCount=0;
        int validBookStatusCount=0;
        int validBookConditionCount=0;

        for(BookGenre genre: BookGenre.values()){
            if(book.getBookGenre()  == genre){
                validBookGenreCount +=1;
            }
        }
        for(BookSource source: BookSource.values()){
            if(book.getBookSource()  == source){
                validBookSourceCount +=1;
            }
        }

        for(BookStatus status: BookStatus.values()){
            if(book.getBookStatus()  == status){
                validBookStatusCount +=1;
            }
        }

        for(BookCondition condition: BookCondition.values()){
            if(book.getBookCondition()  == condition){
                validBookConditionCount +=1;
            }
        }


        if (validBookGenreCount!=1){
            throw new BadInputException("Not a valid Genre");
        }
        if (validBookSourceCount!=1){
            throw new BadInputException("Not a valid Source");
        }
        if (validBookStatusCount!=1){
            throw new BadInputException("Not a valid Status");
        }
        if (validBookConditionCount!=1){
            throw new BadInputException("Not a valid Condition");
        }
*/
        bookRepository.save(book);
        return("Book was created Successfully");
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
    public List<BookEntity> getBookByDateTimeAdded(LocalDateTime dateAdded){
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
    public List<BookEntity> getBookByDateAdded(String date){
        LocalDate day=LocalDate.parse(date);
        LocalDateTime start= day.atStartOfDay();
        LocalDateTime end=day.plusDays(1).atStartOfDay();
        return bookRepository.findByDateAddedGreaterThanEqualAndDateAddedLessThan(start,end);
    }
    @Transactional 
    public String updateBook(Integer id, BookEntity entity){
        Optional<BookEntity> book = getBookById(id);
        BookEntity record=book.get();
        
        List<LoanEntity> loan = loanRepository.findByBookId(entity.getId());

        for (LoanEntity item : loan) {
            if(item.getLoanStatus()!=LoanStatus.Returned){
                throw new BadInputException("Book is already on a Loan and cannot be updated unless returned");
            }
            else if (item.getLoanStatus()==LoanStatus.Returned){
                record.setBookStatus(entity.getBookStatus());
            }
            else{
                throw new MissingInputException("Loan Not Found");
            }
        }
        
        if(!(entity.getTitle().length() <=3) || !(entity.getTitle().length()>=500)){
            throw new BadInputException("Book Title is less than 3 characters or greater than 500 characaters");
        }
        if(!(entity.getAuthor().length() <=3) || !(entity.getAuthor().length()>=80)){
            throw new BadInputException("Book Author is less than 3 characters or greater than 80 characaters");
        }
        if(bookRepository.existsByTitleAndBookAuthor(entity.getTitle(), entity.getAuthor())){
            throw new BadInputException("Book With the Same Title and Same Author Already Exists");
        }

        record.setTitle(entity.getTitle());
        record.setBookGenre(entity.getBookGenre());
        record.setBookCondition(entity.getBookCondition());
        record.setBookSource(entity.getBookSource());
        
        bookRepository.save(record);
        return ("Book has been updated sucessfully");

    }
    /*
    @Valid 
    public List<BookEntity> bulkCreate(List<BookEntity> books){
        List<BookEntity> booksSaved= new ArrayList<>();
        for (BookEntity book : books) {
            booksSaved.add(book);
            
        }
        return booksSaved;
    }
         */


    @Transactional 
    public String removeBook(Integer id){
        Optional<BookEntity> book= getBookById(id);
        if (book.isEmpty()){
            throw new MissingInputException("Book does not exist");
        }
        BookEntity record=book.get();
        record.setBookStatus(BookStatus.Removed);
        return ("book has been updateed to removed");
    }

    @Transactional 
    public String delete(Integer id){
        Optional<BookEntity> book= getBookById(id);
        if (book.isEmpty()){
            throw new MissingInputException("Book does not exist");
        }
        BookEntity record=book.get();
        List<LoanEntity> loans = loanRepository.findByBookId(record.getId());
        if (loans !=null){
            throw new BadInputException("A loan already exists for this book");
        }
        bookRepository.delete(record);
        return ("Book deleted");
    }

    @Transactional 
    public String strictDelete(Integer id){
        Optional<BookEntity> book= getBookById(id);
        if(book.isEmpty()){
            throw new MissingInputException("Book does not exist");
        }
        BookEntity record=book.get();
        List<LoanEntity> loans = loanRepository.findByBookId(record.getId());
        for (LoanEntity loan : loans) {
            if (loan.getBookId() == record.getId()){
                loanRepository.delete(loan);
                bookRepository.delete(record);
            }
            else{
                throw new MissingInputException("No loans associated with this book is found, use deleteBook");
            }
        }
        return ("Books and loans associated were deleted");
    }

}
