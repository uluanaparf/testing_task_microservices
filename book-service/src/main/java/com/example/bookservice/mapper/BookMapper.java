package com.example.bookservice.mapper;

import com.example.bookservice.dto.BookRequestDto;
import com.example.bookservice.dto.BookResponseDto;
import com.example.bookservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {


    BookResponseDto toResponseDto(Book book);
    Book toEntity(BookRequestDto bookRequestDto);
    BookRequestDto toRequestDto(Book book);

}
