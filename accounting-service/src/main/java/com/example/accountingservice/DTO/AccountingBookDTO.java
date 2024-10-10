package com.example.accountingservice.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AccountingBookDTO {

    @NotNull(message = "Book ID cannot be null")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long bookId;

    @Schema(description = "Время получения книги")
    private LocalDateTime borrowedAt;

    @Schema(description = "Время возврата книги", example = "2024-08-01'T'07:10")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime returnBy;

    public AccountingBookDTO() {}

    public AccountingBookDTO(LocalDateTime borrowedAt, LocalDateTime returnBy) {
        this.borrowedAt = borrowedAt;
        this.returnBy = returnBy;
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

    public LocalDateTime getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDateTime returnBy) {
        this.returnBy = returnBy;
    }
}
