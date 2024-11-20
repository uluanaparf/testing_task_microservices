package com.example.accountingservice.dto;

import java.time.LocalDateTime;

public class AccountingBookResponseDto {

    private Long bookId;
    private String title;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedBy;

    public Long getBookId() {
        return bookId;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getReturnedBy() {
        return returnedBy;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
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
