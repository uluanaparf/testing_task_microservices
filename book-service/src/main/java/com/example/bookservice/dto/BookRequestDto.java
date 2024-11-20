package com.example.bookservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestDto {

    @NotBlank(message = "ISBN cannot be null")
    @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters")
    @Schema(description = "Идентификационный номер книги", example = "9465345728461")
    private String isbn;

    @Schema(description = "Название книги", example = "Frankenstein")
    @NotBlank(message = "Title cannot be null")
    private String title;

    @Schema(description = "Жанр книги", example = "Roman")
    @NotBlank(message = "Genre cannot be null")
    private String genre;

    @Schema(description = "Описание книги", example = "About the life and works of scientist Victor Frankenstein")
    private String description;

    @Schema(description = "Автор книги", example = "Mary Shelley")
    @NotBlank(message = "Author cannot be null")
    private String author;

    public @NotBlank(message = "ISBN cannot be null") @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN cannot be null") @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters") String isbn) {
        this.isbn = isbn;
    }

    public @NotBlank(message = "Title cannot be null") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title cannot be null") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Genre cannot be null") String getGenre() {
        return genre;
    }

    public void setGenre(@NotBlank(message = "Genre cannot be null") String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotBlank(message = "Author cannot be null") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author cannot be null") String author) {
        this.author = author;
    }
}
