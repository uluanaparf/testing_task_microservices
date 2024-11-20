package com.example.accountingservice.controller;

import com.example.accountingservice.dto.AccountingBookRequestDto;
import com.example.accountingservice.dto.AccountingBookResponseDto;
import com.example.accountingservice.service.AccountingBookService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounting-book")
public class AccountingBookController {

    @Autowired
    private AccountingBookService accountingBookService;

    @Tag(name = "Получение данных книги", description = "Позволяет получить учет свободных книг")
    @Operation(
            summary = "Получение списка книг",
            description = "Позволяет получить список всех книг (свободных и занятых)"
    )
    @GetMapping
    public ResponseEntity<List<AccountingBookResponseDto>> getAllBooks() {
        List<AccountingBookResponseDto> books = accountingBookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка занятых книг",
            description = "Позволяет получить список книг, которые заняты"
    )
    @Tag(name = "Получение данных книги", description = "Позволяет получить учет свободных книг")
    @GetMapping("/borrowed")
    public ResponseEntity<List<AccountingBookResponseDto>> getBorrowedBooks() {
        List<AccountingBookResponseDto> borrowedBooks = accountingBookService.getBorrowedBooks();
        return new ResponseEntity<>(borrowedBooks, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка свободных книг",
            description = "Позволяет получить список книг, которые свободны"
    )
    @Tag(name = "Получение данных книги", description = "Позволяет получить учет свободных книг")
    @GetMapping("/returned")
    public ResponseEntity<List<AccountingBookResponseDto>> getReturnedBooks() {
        List<AccountingBookResponseDto> returnedBooks = accountingBookService.getReturnedBooks();
        return new ResponseEntity<>(returnedBooks, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение книги по id",
            description = "Позволяет получить конкретную книгу"
    )
    @Tag(name = "Получение данных книги", description = "Позволяет получить учет свободных книг")
    @GetMapping("/{id}")
    public ResponseEntity<AccountingBookResponseDto> getAccountingBookById(@PathVariable Long id) {
        AccountingBookResponseDto accountingBook = accountingBookService.getAccountingBookById(id);
        return new ResponseEntity<>(accountingBook, HttpStatus.OK);
    }

    @Tag(name = "Обновление книги", description = "Позволяет указать время возврата книги")
    @PutMapping("/{id}/return-by")
    public ResponseEntity<AccountingBookResponseDto> updateAccountingBook(@PathVariable Long id, @RequestBody AccountingBookRequestDto accountingBookRequestDto) {
        AccountingBookResponseDto updateAccountingBook = accountingBookService.updateAccountingBook(id, accountingBookRequestDto);
        return new ResponseEntity<>(updateAccountingBook, HttpStatus.OK);
    }

    @Hidden
    @Tag(name = "Создание новой книги", description = "Позволяет создать новую книгу")
    @PostMapping
    public ResponseEntity<AccountingBookResponseDto> addAccountingBook(@RequestBody AccountingBookRequestDto accountingBookRequestDto) {
        AccountingBookResponseDto createdBook = accountingBookService.addAccountingBook(accountingBookRequestDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }
}