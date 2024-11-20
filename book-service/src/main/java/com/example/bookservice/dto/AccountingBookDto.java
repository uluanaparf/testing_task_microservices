package com.example.bookservice.dto;

import java.time.LocalDateTime;

public class AccountingBookDto {
    private Long bookId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedBy;

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
}
