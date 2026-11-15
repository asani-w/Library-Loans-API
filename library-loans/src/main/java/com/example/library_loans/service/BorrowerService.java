package com.example.library_loans.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.repository.BorrowerRepository;

@Service 
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;


    public BorrowerService(BorrowerRepository borrowerRepository){
        this.borrowerRepository=borrowerRepository;
    }

    public List<BorrowerEntity> getBorrowers(){
        return borrowerRepository.findAll();
    }

    public Optional<BorrowerEntity> getBorrowerById(Integer id){
        return borrowerRepository.findById(id);
    }
    
    public BorrowerEntity createBorrower(BorrowerEntity borrower){
        return borrowerRepository.save(borrower);
    }


}
