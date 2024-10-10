package com.example.bookservice.controller;

import com.example.bookservice.DTO.BookDTO;
import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @Tag(name="Получение данных о книгах",description = "Позволяет увидеть список всех книг или информацию о конкретной книге")
    @Operation(
            summary = "Получение книги по id",
            description = "Позволяет получить информацию о конкретной книге"
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Tag(name="Получение данных о книгах",description = "Позволяет увидеть список всех книг или информацию о конкретной книге")
    @Operation(
            summary = "Получение книги по isbn",
            description = "Позволяет получить информацию о конкретной книге"
    )
    @GetMapping("isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        try {
            Book book = bookService.getBookByIsbn(isbn);
            return ResponseEntity.ok(book);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Tag(name="Добавление книги", description = "Позволяет добавить новую книгу в базу данных")
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Book addBook = bookService.addBook(bookDTO);
            return ResponseEntity.ok(addBook);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name="Редактирование книги", description = "Позволяет отредактировать книгу")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Book updatedBook = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok(updatedBook);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @Tag(name="Удаление книги", description = "Позволяет удалить книгу из базы данных")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
