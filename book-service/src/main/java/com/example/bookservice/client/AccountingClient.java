package com.example.bookservice.client;

import com.example.accountingservice.DTO.AccountingBookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accounting-service")
public interface AccountingClient {
    @PostMapping("/auth/API/accountingBook")
    void addBook(@RequestBody AccountingBookDTO accountingBookDTO);
}