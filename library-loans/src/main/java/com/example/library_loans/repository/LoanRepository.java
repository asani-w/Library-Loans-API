package com.example.library_loans.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_loans.entity.LoanEntity;


public interface LoanRepository extends JpaRepository<LoanEntity,Integer> {

    List<LoanEntity>findByBookId(Integer bookId);
    List<LoanEntity>findByBorrowerId(Integer borrowerId);
    List<LoanEntity>findByDateBorrowed(LocalDateTime dateBorrowed);
    List<LoanEntity>findByDateDue(LocalDateTime dateDue);
    
}
