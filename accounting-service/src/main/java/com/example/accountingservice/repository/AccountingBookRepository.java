package com.example.accountingservice.repository;

import com.example.accountingservice.model.AccountingBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountingBookRepository extends JpaRepository<AccountingBook, Long> {
    List<AccountingBook> findByReturnedByIsNull();
    List<AccountingBook> findByReturnedByIsNotNull();
}
