package com.example.library_loans.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_loans.entity.BorrowerEntity;
import com.example.library_loans.entity.BorrowerEntity.MembershipStatus;
import com.example.library_loans.service.BorrowerService;

@RequestMapping ("/api/Borrowers")
@RestController 
public class BorrowerController {
    
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService){
        this.borrowerService=borrowerService;
    }

    @GetMapping("/")
    public List<BorrowerEntity> getAllBorrowers(){
        return borrowerService.getBorrowers();
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
    

    @GetMapping("/dateCreated/date/{date}")
    public List<BorrowerEntity>getBorrowerByDateCreated(@PathVariable String date){
        return borrowerService.getBorrowerByDateCreated(date);
    }
    @GetMapping("/dateCreated/dateTime/{dateTime}")
    public List<BorrowerEntity>getBorrowerByDateTimeCreated(@PathVariable LocalDateTime dateTime){
        return borrowerService.getBorrowerByDateTimeCreated(dateTime);
    }

    @GetMapping("/dateExpired/date/{date}")
    public List<BorrowerEntity>getBorrowerByDateExpired(@PathVariable String date){
        return borrowerService.getBorrowerByDateExpired(date);
    }

    @GetMapping("/dateExpired/dateTime/{dateTime}")
    public List<BorrowerEntity>getBorrowerByDateTimeExpired(@PathVariable LocalDateTime dateTime){
        return borrowerService.getBorrowerByDateTimeExpired(dateTime);
    }

    @GetMapping("/MembershipStatus/{status}")
    public List<BorrowerEntity> getBorrowerByMembershipStatus(@PathVariable MembershipStatus status){
        return borrowerService.getBorrowerByMembershipStatus(status);
    }

    @PostMapping
    public ResponseEntity<String> create( @RequestBody BorrowerEntity borrower){
        String message= borrowerService.createBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update( @PathVariable Integer id, @RequestBody BorrowerEntity entity ){
        String message = borrowerService.update(id,entity);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PutMapping("/suspend/{id}")
    public ResponseEntity<String> suspendBorrower( @PathVariable Integer id){
        String message =borrowerService.suspend(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        String message =borrowerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @DeleteMapping ("/strictDelete/{id}")
    public ResponseEntity<String> strictDelete(@PathVariable Integer id){
        String message= borrowerService.strictDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
     
    //bulk adding borrowers
    @PostMapping ("/bulk")
    public ResponseEntity<String>createBulk( @RequestBody List<BorrowerEntity> borrowers){
        String records=borrowerService.bulkCreate(borrowers);
        return ResponseEntity.status(HttpStatus.CREATED).body(records);

    }
    
}

