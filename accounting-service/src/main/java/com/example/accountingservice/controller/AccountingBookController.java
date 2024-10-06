package com.example.accountingservice.controller;

import com.example.accountingservice.DTO.AccountingBookDTO;
import com.example.accountingservice.model.AccountingBook;
import com.example.accountingservice.service.AccountingBookService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/accountingBook")
public class AccountingBookController {

    @Autowired
    private AccountingBookService accountingBookService;

    @GetMapping
    public List<AccountingBook> getAllBooks(){
        return accountingBookService.getAllBooks();
    }

    @GetMapping("/borrowed")
    public ResponseEntity<List<AccountingBook>> getBorrowedBooks() {
        List<AccountingBook> borrowedBooks = accountingBookService.getBorrowedBooks();
        return new ResponseEntity<>(borrowedBooks, HttpStatus.OK);
    }

    @GetMapping("/returned")
    public ResponseEntity<List<AccountingBook>> getReturnedBooks() {
        List<AccountingBook> returnedBooks = accountingBookService.getReturnedBooks();
        return new ResponseEntity<>(returnedBooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountingBookById(@PathVariable Long id) {
        try {
           AccountingBook accountingBook = accountingBookService.getAccountingBookById(id);
            return new ResponseEntity<>(accountingBook, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/return-by")
    public ResponseEntity<?> updateReturnBy(@PathVariable Long id, @RequestBody LocalDateTime returnBy) {
        try {
            AccountingBook updatedAccountingBook = accountingBookService.updateReturnBy(id, returnBy);
            return new ResponseEntity<>(updatedAccountingBook, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAccountingBook(@Valid @RequestBody AccountingBookDTO accountingBookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            AccountingBook addAccountingBook = accountingBookService.addAccountingBook(accountingBookDTO);
            return new ResponseEntity<>(addAccountingBook, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

