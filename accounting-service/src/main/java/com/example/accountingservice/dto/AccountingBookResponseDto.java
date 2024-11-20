package com.example.accountingservice.dto;

import java.time.LocalDateTime;

public class AccountingBookResponseDto {

    private Long bookId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnBy;

    public Long getBookId() {
        return bookId;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getReturnBy() {
        return returnBy;
    }
}
