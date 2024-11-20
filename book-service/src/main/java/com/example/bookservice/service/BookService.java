package com.example.bookservice.service;


import com.example.bookservice.dto.AccountingBookDto;
import com.example.bookservice.dto.BookRequestDto;
import com.example.bookservice.dto.BookResponseDto;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookservice.client.AccountingClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;


    @Autowired
    private AccountingClient accountingClient;

    public List<BookResponseDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Such book not found"));
        return bookMapper.toResponseDto(book);
    }

    public BookResponseDto getBookByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NoSuchElementException("Such book not found"));
        return bookMapper.toResponseDto(book);
    }

    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto){

        if (bookRepository.findByIsbn(bookRequestDto.getIsbn()).isPresent()) {
            throw new IllegalArgumentException("ISBN already exists."); }
        Book book = bookMapper.toEntity(bookRequestDto);
        Book savedBook = bookRepository.save(book);
        AccountingBookDto accountingBookDto = new AccountingBookDto();
        accountingBookDto.setBookId(savedBook.getId());
        accountingBookDto.setBorrowedAt(null);
        accountingBookDto.setReturnedBy(null);
        accountingClient.addBook(accountingBookDto);

        return bookMapper.toResponseDto(savedBook);
    }

    @Transactional
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto){

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("We did not find a book with this id. Try again"));
        Optional<Book> existingBookWithIsbn = bookRepository.findByIsbn(bookRequestDto.getIsbn());
        if (existingBookWithIsbn.isPresent() && !existingBookWithIsbn.get().getId().equals(id)) {
            throw new IllegalArgumentException("ISBN already exists.");
        }
        book.setIsbn(bookRequestDto.getIsbn());
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setDescription(bookRequestDto.getDescription());
        book.setAuthor(bookRequestDto.getAuthor());
        Book updateBook = bookRepository.save(book);
        return bookMapper.toResponseDto(updateBook);
    }

    @Transactional
    public void deleteBook(long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("We did not find a book with this id. Try again"));
        bookRepository.delete(book);
    }
}
