package com.example.library_loans.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.entity.LoanEntity.LoanStatus;

public interface LoanRepository extends JpaRepository<LoanEntity,Integer> {

    List<LoanEntity>findByBookId(Integer bookId);
    List<LoanEntity>findByBorrowerId(Integer borrowerId);
    List<LoanEntity>findByDateBorrowed(LocalDateTime dateBorrowed);
    List<LoanEntity>findByDateDue(LocalDateTime dateDue);
    List<LoanEntity>findByLoanStatus(LoanStatus loanStatus);
    List<LoanEntity>findByDateReturned(LocalDateTime dateReturned);

    List<LoanEntity>findByDateBorrowedGreaterThanEqualAndDateBorrowedLessThan(LocalDateTime start,LocalDateTime end);
    List<LoanEntity>findByDateDueGreaterThanEqualAndDateDueLessThan(LocalDateTime start,LocalDateTime end);
    List<LoanEntity>findByDateReturnedGreaterThanEqualAndDateReturnedLessThan(LocalDateTime start,LocalDateTime end);

    
}
