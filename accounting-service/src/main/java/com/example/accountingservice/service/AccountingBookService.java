package com.example.accountingservice.service;

import com.example.accountingservice.DTO.AccountingBookDTO;
import com.example.accountingservice.model.AccountingBook;
import com.example.accountingservice.repository.AccountingBookRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountingBookService {

    @Autowired
    private AccountingBookRepository accountingBookRepository;

    @Autowired
    private Validator validator;

    public List<AccountingBook> getAllBooks(){
        return accountingBookRepository.findAll();
    }

    public List<AccountingBook> getBorrowedBooks(){
        return accountingBookRepository.findByReturnByIsNull();
    }

    public List<AccountingBook> getReturnedBooks() {
        return accountingBookRepository.findByReturnByIsNotNull();
    }

    public AccountingBook getAccountingBookById(Long id) {
        return accountingBookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Such book not found"));
    }

    public AccountingBook addAccountingBook(AccountingBookDTO accountingBookDTO) {
        validator.validate(accountingBookDTO);
        AccountingBook accountingBook = new AccountingBook();
        accountingBook.setBookId(accountingBookDTO.getBookId());
        accountingBook.setBorrowedAt(accountingBookDTO.getBorrowedAt());
        accountingBook.setReturnBy(accountingBookDTO.getReturnBy());
        return accountingBookRepository.save(accountingBook);
    }


    public AccountingBook updateReturnBy(Long id, LocalDateTime returnBy) {
        AccountingBook accountingBook = accountingBookRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("We did not find a book with this id. Try again"));
        accountingBook.setReturnBy(returnBy);
        return accountingBookRepository.save(accountingBook);
    }
}
