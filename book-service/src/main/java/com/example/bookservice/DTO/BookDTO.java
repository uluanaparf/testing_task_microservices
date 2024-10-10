package com.example.bookservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO {

    @NotNull(message = "ISBN cannot be null")
    @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters")
    @Schema(description = "Идентификационный номер книги", example = "9465345728461")
    private String isbn;

    @Schema(description = "Название книги", example = "Frankenstein")
    @NotNull(message = "Title cannot be null")
    private String title;

    @Schema(description = "Жанр книги", example = "Roman")
    @NotNull(message = "Genre cannot be null")
    private String genre;

    @Schema(description = "Описание книги", example = "About the life and works of scientist Victor Frankenstein")
    private String description;

    @Schema(description = "Автор книги", example = "Mary Shelley")
    @NotNull(message = "Author cannot be null")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}