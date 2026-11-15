package com.example.library_loans.controller;

import com.example.library_loans.repository.BorrowerRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.service.BorrowerService;

import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping ("/api/Borrowers")
@RestController 
public class BorrowerController {
    
    private final BorrowerRepository borrowerRepository;
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService, BorrowerRepository borrowerRepository){
        this.borrowerService=borrowerService;
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping("/")
    public List<BorrowerEntity> getAllBorrowers(){
        return borrowerRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<BorrowerEntity> getBorrowerById(@PathVariable Integer id){
        return borrowerService.getBorrowerById(id);
    }

    @GetMapping("/firstName/{fname}")
    public List<BorrowerEntity>getBorrowerByFirstName(@PathVariable String fname){
        return borrowerService.getBorrowerByFirstName(fname);
    }

    @GetMapping("/lastName/{lname}")
    public List<BorrowerEntity>getBorrowerByLastName(@PathVariable String lname){
        return borrowerService.getBorrowerByLastName(lname);
    }
    @GetMapping("/email/{email}")
    public List<BorrowerEntity>getBorrowerByEmail(@PathVariable String email){
        return borrowerService.getBorrowerByEmail(email);
    }
    @GetMapping("/telephone/{tel}")
    public List<BorrowerEntity>getBorrowerByTelephoneNumber(@PathVariable String tel){
        return borrowerService.getBorrowerByTelephoneNumber(tel);
    }
    /*

    @GetMapping("/dateCreated/{date}")
    public List<BorrowerEntity>getBorrowerByTitle(@PathVariable String title){
        return borrowerService.getBorrowerByFirstName(title)
    }
        
     */

    @PostMapping
    public BorrowerEntity create(@RequestBody BorrowerEntity borrower){
        return borrowerService.createBorrower(borrower);
    }
}

