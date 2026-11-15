package com.example.library_loans.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BrorrowerEntity;
import com.example.library_loans.repository.BorrowerRepository;

@Service 
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;


    public BorrowerService(BorrowerRepository borrowerRepository){
        this.borrowerRepository=borrowerRepository;
    }

    public Optional<BrorrowerEntity> getBorrowerById(Integer id){
        return borrowerRepository.findById(id);
    }
    
    public BrorrowerEntity createBorrower(BrorrowerEntity borrower){
        return borrowerRepository.save(borrower);
    }


}
