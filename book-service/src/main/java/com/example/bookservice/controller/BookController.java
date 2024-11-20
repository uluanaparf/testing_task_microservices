package com.example.bookservice.controller;

import com.example.bookservice.dto.BookRequestDto;
import com.example.bookservice.dto.BookResponseDto;
import com.example.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Tag(name="Получение данных о книгах",description = "Позволяет увидеть список всех книг или информацию о конкретной книге")
    @Operation(
            summary = "Получение списка книг",
            description = "Позволяет увидеть все книги находящиеся в базе"
    )
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Tag(name="Получение данных о книгах",description = "Позволяет увидеть список всех книг или информацию о конкретной книге")
    @Operation(
            summary = "Получение книги по id",
            description = "Позволяет получить информацию о конкретной книге"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Tag(name="Получение данных о книгах",description = "Позволяет увидеть список всех книг или информацию о конкретной книге")
    @Operation(
            summary = "Получение книги по isbn",
            description = "Позволяет получить информацию о конкретной книге"
    )
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseDto> getBookByIsbn(@PathVariable String isbn) {
        BookResponseDto book = bookService.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Tag(name="Добавление книги", description = "Позволяет добавить новую книгу в базу данных")
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto createbook = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(createbook, HttpStatus.CREATED);
    }

    @Tag(name="Редактирование книги", description = "Позволяет отредактировать книгу")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto updateBook = bookService.updateBook(id, bookRequestDto);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }
    @Tag(name="Удаление книги", description = "Позволяет удалить книгу из базы данных")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}