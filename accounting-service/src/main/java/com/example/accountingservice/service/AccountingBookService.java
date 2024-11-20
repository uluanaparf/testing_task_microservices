package com.example.accountingservice.service;

import com.example.accountingservice.dto.AccountingBookRequestDto;
import com.example.accountingservice.dto.AccountingBookResponseDto;
import com.example.accountingservice.mapper.AccountingBookMapper;
import com.example.accountingservice.model.AccountingBook;
import com.example.accountingservice.repository.AccountingBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AccountingBookService {

    @Autowired
    private AccountingBookRepository accountingBookRepository;

    @Autowired
    private AccountingBookMapper accountingBookMapper;

    public List<AccountingBookResponseDto> getAllBooks(){
        List<AccountingBook> books = accountingBookRepository.findAll();
        return books.stream()
                .map(accountingBookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AccountingBookResponseDto> getBorrowedBooks(){
        List<AccountingBook> books = accountingBookRepository.findByReturnedByIsNull();
        return  books.stream()
                .map(accountingBookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AccountingBookResponseDto> getReturnedBooks() {
        List<AccountingBook> books = accountingBookRepository.findByReturnedByIsNotNull();
        return  books.stream()
                .map(accountingBookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public AccountingBookResponseDto getAccountingBookById(Long id) {
        AccountingBook accountingBook = accountingBookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Such book not found"));
        return accountingBookMapper.toResponseDto(accountingBook);
    }

    @Transactional
    public AccountingBookResponseDto addAccountingBook(AccountingBookRequestDto accountingBookRequestDto) {
        AccountingBook accountingBook = accountingBookMapper.toEntity(accountingBookRequestDto);
        accountingBook.setBookId(accountingBookRequestDto.getBookId());
        AccountingBook savedBook = accountingBookRepository.save(accountingBook);
        return accountingBookMapper.toResponseDto(savedBook);
    }

    @Transactional
    public AccountingBookResponseDto updateAccountingBook(Long id, AccountingBookRequestDto accountingBookRequestDto) {
        AccountingBook accountingBook = accountingBookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("We did not find a book with this id. Try again"));
        accountingBook.setReturnedBy(accountingBookRequestDto.getReturnedBy());
        accountingBook.setBorrowedAt(accountingBookRequestDto.getBorrowedAt());
        AccountingBook updatedBook = accountingBookRepository.save(accountingBook);
        return accountingBookMapper.toResponseDto(updatedBook);
    }
}
