package com.example.bookservice.client;

import com.example.bookservice.dto.AccountingBookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accounting-service")
public interface AccountingClient {

    @PostMapping("/accounting-book")
    void addBook(@RequestBody AccountingBookDto accountingBookDto);
}