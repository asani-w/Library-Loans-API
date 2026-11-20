package com.example.library_loans.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCategory;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.repository.BookRepository;
import com.example.library_loans.repository.LoanRepository;

@SpringBootTest 
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BookServiceTest{

    @Mock 
    BookRepository bookRepository;
    @Mock 
    LoanRepository loanRepository;
    @InjectMocks 
    BookService bookService;

    BookEntity makeValidBookEntity_one (){
        BookEntity book = new BookEntity();
        book.setTitle("The Funny Man");
        book.setAuthor("Mario Henry");
        book.setBookGenre(BookGenre.Comic);
        book.setBookSource(BookSource.Distributor);
        book.setBookStatus(BookStatus.Available);
        book.setBookCondition(BookCondition.New);
        book.setDateAdded(LocalDateTime.of(2026, 11, 1, 20, 5));
        return book;
    }

    BookEntity makeInValidBookEntity_one (){
        BookEntity book = new BookEntity();
        book.setTitle("N");
        book.setAuthor("Gregory Marshall");
        book.setBookGenre(BookGenre.Thriller);
        book.setBookSource(BookSource.Distributor);
        book.setBookStatus(BookStatus.Available);
        book.setBookCondition(BookCondition.New);
        return book;
    }

    //Create
    void createBook_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.save(book)).thenReturn(book);

        String record = bookService.createBook(book);
        assertEquals("Book was created Successfully", record);
    }

    void createBook_UnHappyPath(){
        BookEntity book = makeInValidBookEntity_one();
        when(bookRepository.save(book)).thenReturn(null);

        String record = bookService.createBook(book);
        assertEquals(null, record);
    }

    //Update
    void updateBook_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.save(book)).thenReturn(book);

        String record = bookService.updateBook(book.getId(),book);
        assertEquals("Book has been updated sucessfully", record);
    }

    void updateBook_UnHappyPath(){
        BookEntity book = makeInValidBookEntity_one();
        when(bookRepository.save(book)).thenReturn(null);

        String record = bookService.updateBook(book.getId(),book);
        assertEquals(null, record);
    }
    //Delete

    //retrievers
    @Test 
    void findById_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Optional<BookEntity> result = bookService.getBookById(1);
        assertTrue(result.isPresent());
        assertEquals(book, result.get());
    }

    @Test 
    void findById_UnHappyPath_WrongId(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findById(10)).thenReturn(Optional.of(book));

        Optional<BookEntity> result = bookService.getBookById(10);
        assertTrue(result.isPresent());
        assertEquals(book, result.get());

    }
    @Test 
    void findBookByTitle_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByTitle("The Happy Man")).thenReturn(List.of(book));
    
        List<BookEntity> result=bookService.getBookByTitle("The Happy Man");
        assertEquals(List.of(book),result);
    
}


    @Test 
    void findBookByTitle_UnHappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByTitle("The UnHappy Man")).thenReturn(List.of());

        List<BookEntity> result=bookService.getBookByTitle("The UnHappy Man");
        assertTrue(result.isEmpty());
        assertNotEquals(book, result);
    
    }

    @Test 
    void findBookByAuthor_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookAuthor("Mario Henry")).thenReturn(List.of(book));
    
        List<BookEntity> result=bookService.getBookByBookAuthor("Mario Henry");
        assertEquals(List.of(book),result);
    
}
    @Test 
    void findBookByAuthor_UnHappyPath(){
        when(bookRepository.findByBookAuthor("Jamie Lowe")).thenReturn(List.of());
        List<BookEntity> result=bookService.getBookByBookAuthor("Jamie Lowe");
        assertTrue(result.isEmpty());
    }
    
    void findBookByGenre_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookGenre(BookGenre.Comic)).thenReturn(List.of(book));
    
        List<BookEntity> result=bookService.getBookByBookGenre(BookGenre.Comic);
        assertEquals(List.of(book),result);
    }

    @Test 
    void findBookByGenre_UnHappyPath(){
        when(bookRepository.findByBookGenre(BookGenre.Cook_Books)).thenReturn(List.of());
        List<BookEntity> result=bookService.getBookByBookGenre(BookGenre.Cook_Books);
        assertTrue(result.isEmpty());

    }
    @Test 
    void findBookByDateAdded_HappyPath(){
        BookEntity book=makeValidBookEntity_one();
        LocalDateTime date = LocalDateTime.of(2026, 11, 1, 20, 5);
        when(bookRepository.findByDateAddedGreaterThanEqualAndDateAddedLessThan(date.toLocalDate().atStartOfDay(),date.toLocalDate().plusDays(1).atStartOfDay())).thenReturn(List.of(book));
        List<BookEntity> result=bookService.getBookByDateAdded("2026-11-01");
        assertEquals(List.of(book), result);
    }

    @Test 
    void findBookByDateAdded_UnHappyPath(){
        LocalDateTime date = LocalDateTime.of(2026, 11, 1, 20, 5);
        when(bookRepository.findByDateAddedGreaterThanEqualAndDateAddedLessThan(date.toLocalDate().atStartOfDay(),date.toLocalDate().plusDays(1).atStartOfDay())).thenReturn(List.of());
        
        List<BookEntity> result = bookService.getBookByDateAdded("2026-11-01");
        assertTrue(result.isEmpty());
    }
    @Test 
    void findBookByDateTimeAdded_HappyPath(){
        BookEntity book=makeValidBookEntity_one();
        LocalDateTime date = LocalDateTime.of(2026, 11, 1, 20, 5);

        when(bookRepository.findByDateAdded(date)).thenReturn(List.of(book));
        List<BookEntity> result=bookService.getBookByDateTimeAdded(date);
        assertEquals(List.of(book), result);
    }

    @Test 
    void findBookByDateTimeAdded_UnHappyPath(){

        LocalDateTime date = LocalDateTime.of(2026, 11, 1, 20, 5);
        when(bookRepository.findByDateAdded(date)).thenReturn(List.of());
        
        List<BookEntity> result = bookService.getBookByDateTimeAdded(date);
        assertTrue(result.isEmpty());
    }

    @Test 
    void findBookBySource_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookSource(BookSource.Distributor)).thenReturn(List.of(book));

        List<BookEntity> result=bookService.getBookByBookSource(BookSource.Distributor);
        assertEquals(List.of(book), result);
    }

    @Test 
    void findBookBySource_UnHappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookSource(BookSource.Donation)).thenReturn(List.of());

        List<BookEntity> result=bookService.getBookByBookSource(BookSource.Donation);
        assertNotEquals(List.of(book), result);
        assertTrue(result.isEmpty());
    }

     @Test  
     void findBookByStatus_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookStatus(BookStatus.Available)).thenReturn(List.of(book));

        List<BookEntity> result=bookService.getBookByStatus(BookStatus.Available);
        assertEquals(List.of(book), result);

    }
    @Test  
    void findBookByStatus_UnHappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookStatus(BookStatus.Borrowed)).thenReturn(List.of());

        List<BookEntity> result=bookService.getBookByStatus(BookStatus.Borrowed);
        assertNotEquals(List.of(book), result);
        assertTrue(result.isEmpty());
    }

    @Test 
    void findBookByCondition_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookCondition(BookCondition.New)).thenReturn(List.of(book));

        List<BookEntity> result=bookService.getBookByCondition(BookCondition.New);
        assertEquals(List.of(book), result);
        assertFalse(result.isEmpty());
    }
    @Test 
    void findBookByCondition_UnHappyPath(){
        BookEntity book = makeValidBookEntity_one();
        when(bookRepository.findByBookCondition(BookCondition.Poor)).thenReturn(List.of());

        List<BookEntity> result=bookService.getBookByCondition(BookCondition.Poor);
        assertNotEquals(List.of(book), result);
        assertTrue(result.isEmpty());
    }
    @Test 
    void findBookByCategory_HappyPath(){
        BookEntity book = makeValidBookEntity_one();
        BookCategory category = BookGenre.Comic.getCategory();

        when(bookRepository.findByBookGenreIn(anyList())).thenReturn(List.of(book));

        List<BookEntity> result=bookService.getBookByBookCategory(category);
        assertEquals(List.of(book), result);
    }

    @Test 
    void findBookByCategory_UnHappyPath(){
        BookCategory category = BookGenre.Autobiography.getCategory();

        when(bookRepository.findByBookGenreIn(anyList())).thenReturn(List.of());
        List<BookEntity> result = bookService.getBookByBookCategory(category);
        assertTrue(result.isEmpty());
    }
    /*
     @Test 
     findBookByDateAdded_UnHappyPath(){
        
    }
      */
}