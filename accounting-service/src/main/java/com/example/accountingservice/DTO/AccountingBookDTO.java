package com.example.accountingservice.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AccountingBookDTO {
    @NotNull(message = "Book ID cannot be null")
    private Long bookId;

    private LocalDateTime borrowedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime returnBy;

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

    public LocalDateTime getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDateTime returnBy) {
        this.returnBy = returnBy;
    }
}
