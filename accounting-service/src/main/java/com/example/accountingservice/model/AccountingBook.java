package com.example.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table (name = "accounting_books")
public class AccountingBook {

    @Id
    private Long bookId;

    private LocalDateTime borrowedAt;
    private LocalDateTime returnedBy;
    private String title;


    public AccountingBook() {
    }

    public AccountingBook(Long bookId, LocalDateTime borrowedAt, LocalDateTime returnedBy, String title) {
        this.bookId = bookId;
        this.borrowedAt = borrowedAt;
        this.returnedBy = returnedBy;
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getReturnedBy() {
        return returnedBy;
    }

    public void setReturnedBy(LocalDateTime returnedBy) {
        this.returnedBy = returnedBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
