package com.example.library_loans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.library_loans.entity.BookEntity;
import com.example.library_loans.entity.BookEntity.BookCondition;
import com.example.library_loans.entity.BookEntity.BookGenre;
import com.example.library_loans.entity.BookEntity.BookSource;
import com.example.library_loans.entity.BookEntity.BookStatus;
import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.BorrowerEntity.MembershipStatus;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.LoanEntity.LoanStatus;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.repository.BookRepository;
import com.example.library_loans.repository.BorrowerRepository;
import com.example.library_loans.repository.LoanRepository;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    LoanRepository loanRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    BorrowerRepository borrowerRepository;
    @Mock
    BookService bookService;
    @Mock
    BorrowerService borrowerService;
    @InjectMocks
    LoanService loanService;

    BookEntity makeBorrowedBook() {
        BookEntity book = new BookEntity();
        book.setTitle("The Funny Man");
        book.setAuthor("Mario Henry");
        book.setBookGenre(BookGenre.Comic);
        book.setBookSource(BookSource.Distributor);
        book.setBookStatus(BookStatus.Borrowed);
        book.setBookCondition(BookCondition.New);
        return book;
    }

    BorrowerEntity makeActiveBorrower() {
        BorrowerEntity borrower = new BorrowerEntity();
        borrower.setFirstName("Maria");
        borrower.setLastName("Johnson");
        borrower.setEmail("maria.johnson@gmail.com");
        borrower.setTelephoneNumber("5551234567");
        borrower.setMembershipStatus(MembershipStatus.Active);
        return borrower;
    }

    @Test
    void createLoan_UnHappyPath_() {
        BookEntity book = makeBorrowedBook();
        BorrowerEntity borrower = makeActiveBorrower();

        LoanEntity existingLoan = new LoanEntity();
        existingLoan.setBookId(1);
        existingLoan.setBorrowerId(2);
        existingLoan.setLoanStatus(LoanStatus.Pending_Return);

        when(bookService.getBookById(1)).thenReturn(Optional.of(book));
        when(borrowerService.getBorrowerById(3)).thenReturn(Optional.of(borrower));
        when(loanRepository.findByBookId(1)).thenReturn(List.of(existingLoan));

        LoanEntity newLoan = new LoanEntity();
        newLoan.setBookId(1);
        newLoan.setBorrowerId(3);

        BadInputException ex = assertThrows(BadInputException.class,() -> loanService.createLoan(newLoan));
        assertEquals("This book is already on loan by another user", ex.getMessage());
    }
}