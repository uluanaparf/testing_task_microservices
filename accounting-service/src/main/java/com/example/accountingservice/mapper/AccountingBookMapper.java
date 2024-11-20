package com.example.accountingservice.mapper;


import com.example.accountingservice.dto.AccountingBookRequestDto;
import com.example.accountingservice.dto.AccountingBookResponseDto;
import com.example.accountingservice.model.AccountingBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountingBookMapper {

    AccountingBookResponseDto toResponseDto(AccountingBook accountingBook);
    AccountingBook toEntity(AccountingBookRequestDto accountingBookRequestDto);
    AccountingBookRequestDto toRequestDto(AccountingBook accountingBook);
}
