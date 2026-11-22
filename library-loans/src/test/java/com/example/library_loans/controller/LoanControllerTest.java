package com.example.library_loans.controller;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.example.library_loans.entity.LoanEntity;
import com.example.library_loans.exception.BadInputException;
import com.example.library_loans.service.LoanService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LoanControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;


    @Test
    void create_UnHappyPath_BookAlreadyOnLoan(){
        LoanEntity loan = new LoanEntity();
        when(loanService.createLoan(loan)).thenThrow(new BadInputException("This book is already on a loan by another user"));

        BadInputException ex= assertThrows(BadInputException.class,() -> loanController.create(loan));

        assertEquals("This book is already on a loan by another user", ex.getMessage());
    }

}