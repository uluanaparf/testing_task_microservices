package com.example.authservice.mapper;

import com.example.authservice.dto.RegistRequestDto;
import com.example.authservice.dto.RegistResponseDto;
import com.example.authservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistMapper {

    User toEntity(RegistRequestDto registRequestDto);
    RegistResponseDto toResponseDto(User user);
}
