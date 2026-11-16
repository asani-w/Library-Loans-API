package com.example.library_loans.service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.BorrowerEntity.MembershipStatus;
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

    public List<BorrowerEntity> getBorrowerByFirstName(String fname){
        return borrowerRepository.findByFirstName(fname);
    }

     public List<BorrowerEntity> getBorrowerByLastName(String lname ){
        return borrowerRepository.findByLastName(lname);
    }

     public List<BorrowerEntity> getBorrowerByEmail(String email ){
        return borrowerRepository.findByEmail(email);
    }

     public List<BorrowerEntity> getBorrowerByTelephoneNumber(String tel){
        return borrowerRepository.findByTelephoneNumber(tel);
    }

     public List<BorrowerEntity> getBorrowerByDateCreated(LocalDateTime date){
        return borrowerRepository.findByDateCreated(date);
    }

    public List<BorrowerEntity> getBorrowerByMembershipStatus(MembershipStatus membershipStatus){
        return borrowerRepository.findByMembershipStatus(membershipStatus);
    }

    public BorrowerEntity update(Integer id, BorrowerEntity entity){
        Optional<BorrowerEntity> borrower = getBorrowerById(id);
        BorrowerEntity record = borrower.get();
        record.setFirstName(entity.getFirstName());
        record.setLastName(entity.getLastName());
        record.setEmail(entity.getEmail());
        record.setTelephoneNumber(entity.getTelephoneNumber());
        record.setMembershipStatus(entity.getMembershipStatus());
        return borrowerRepository.save(record);
    }

    public BorrowerEntity deleteBorrower(Integer id){
        Optional<BorrowerEntity> borrower = getBorrowerById(id);
        BorrowerEntity record= borrower.get();
        borrowerRepository.delete(record);
        return record;
    }
}
