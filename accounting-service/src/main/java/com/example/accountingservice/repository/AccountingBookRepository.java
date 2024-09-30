package com.example.accountingservice.repository;

import com.example.accountingservice.model.AccountingBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountingBookRepository extends JpaRepository<AccountingBook, Long> {
    List<AccountingBook> findByReturnByIsNull();
    List<AccountingBook> findByReturnByIsNotNull();
}
