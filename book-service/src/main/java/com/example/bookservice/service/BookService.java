package com.example.bookservice.service;


import com.example.accountingservice.DTO.AccountingBookDTO;
import com.example.bookservice.DTO.BookDTO;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookservice.client.AccountingClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private AccountingClient accountingClient;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public Book addBook(BookDTO bookDTO){
        validator.validate(bookDTO);
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setDescription(bookDTO.getDescription());
        book.setAuthor(bookDTO.getAuthor());
        Book savedBook = bookRepository.save(book);

        AccountingBookDTO accountingBookDTO = new AccountingBookDTO();
        accountingBookDTO.setBookId(savedBook.getId());
        accountingBookDTO.setBorrowedAt(LocalDateTime.now());
        accountingBookDTO.setReturnBy(null);

        accountingClient.addBook(accountingBookDTO);

        return savedBook;
    }

    public Book updateBook(Long id, BookDTO bookDTO){
        validator.validate(bookDTO);

        Book book = bookRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("We did not find a book with this id. Try again"));
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setDescription(bookDTO.getDescription());
        book.setAuthor(bookDTO.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBook(long id){
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("We did not find a book with this id. Try again"));
        bookRepository.delete(book);
    }
}
