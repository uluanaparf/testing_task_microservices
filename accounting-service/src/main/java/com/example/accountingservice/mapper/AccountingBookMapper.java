package com.example.accountingservice.mapper;


import com.example.accountingservice.dto.AccountingBookRequestDto;
import com.example.accountingservice.dto.AccountingBookResponseDto;
import com.example.accountingservice.model.AccountingBook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountingBookMapper {

    AccountingBookMapper INSTANCE = Mappers.getMapper(AccountingBookMapper.class);

    AccountingBookResponseDto toResponseDto(AccountingBook accountingBook);
    AccountingBook toEntity(AccountingBookRequestDto accountingBookRequestDto);
    AccountingBookRequestDto toRequestDto(AccountingBook accountingBook);
}
