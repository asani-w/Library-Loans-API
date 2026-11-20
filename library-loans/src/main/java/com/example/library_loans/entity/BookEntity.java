package com.example.library_loans.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
public class BookEntity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotNull 
    @Size(min=2,max=500)
    private String title;

    public enum BookCategory{
        Fiction,
        NonFiction
    }

    public enum BookGenre {
        Comic(BookCategory.Fiction),
        Humor(BookCategory.Fiction),
        Fantasy(BookCategory.Fiction),
        Mystery(BookCategory.Fiction),
        Historical_Fiction(BookCategory.Fiction),
        Thriller(BookCategory.Fiction),
        Romance(BookCategory.Fiction),
        Science_Fiction(BookCategory.Fiction),
        Horror(BookCategory.Fiction),
        Young_Adult(BookCategory.Fiction),
        Memoir(BookCategory.NonFiction),
        Self_Help(BookCategory.NonFiction),
        Biography(BookCategory.NonFiction),
        Travel(BookCategory.NonFiction),
        History(BookCategory.NonFiction),
        Cook_Books(BookCategory.NonFiction),
        Autobiography(BookCategory.NonFiction),
        Nature(BookCategory.NonFiction),
        Sport(BookCategory.NonFiction),
        Recreation(BookCategory.NonFiction),
        Music(BookCategory.NonFiction),
        Games(BookCategory.NonFiction);
    
        private final BookCategory bookCategory;

        BookGenre(BookCategory category){
            this.bookCategory=category;
        }
        
        public BookCategory getCategory(){
            return bookCategory;
        }
    }
    @NotNull
    @Enumerated (EnumType.STRING)
    private BookGenre bookGenre;

    
    @Column(updatable=false)
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateAdded;

    public enum BookSource{
        Donation,
        Wholesaler,
        Distributor,
        Publisher,
        Grants;
    }

    @NotNull 
    @Enumerated(EnumType.STRING)
    private BookSource bookSource;

    public enum BookStatus{
        Borrowed,
        Available,
        Reserved,
        Removed;   
    }
    @NotNull 
    @Enumerated (EnumType.STRING)
    private BookStatus bookStatus;

    public enum BookCondition{
        New,
        Excellent,
        Acceptable,
        Poor;
    }

    @NotNull 
    @Enumerated (EnumType.STRING)
    private BookCondition bookCondition;

    @NotNull 
    @Size(min=2,max=50)
    private String bookAuthor;

    public BookEntity() {}

    public Integer getId() {return id;}
    public String getTitle(){return title;}
    public void  setTitle(String title){ this.title=title;}
    public LocalDateTime getDateAdded(){return dateAdded;}
    public void setDateAdded(LocalDateTime dateAdded){ this.dateAdded=dateAdded;}
    public BookSource getBookSource(){return bookSource;}
    public void setBookSource(BookSource bookSource){this.bookSource=bookSource;}
    public BookCondition getBookCondition(){return bookCondition;}
    public void setBookCondition(BookCondition bookCondition){this.bookCondition=bookCondition;}
    public BookStatus getBookStatus(){return bookStatus;}
    public void setBookStatus(BookStatus bookStatus){this.bookStatus=bookStatus;}
    @Schema (accessMode =Schema.AccessMode.READ_ONLY)
    public BookCategory getBookCategory(){return bookGenre.getCategory();}
    public BookGenre getBookGenre(){return bookGenre;}
    public void setBookGenre(BookGenre bookGenre){this.bookGenre=bookGenre;}
    public String getAuthor(){return bookAuthor;}
    public void setAuthor(String bookAuthor){this.bookAuthor=bookAuthor;}
}

