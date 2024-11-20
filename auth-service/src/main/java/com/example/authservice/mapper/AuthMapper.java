package com.example.authservice.mapper;

import com.example.authservice.dto.AuthRequestDto;
import com.example.authservice.dto.AuthResponseDto;
import com.example.authservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toEntity(AuthRequestDto authRequestDto);

    default AuthResponseDto toResponseDto(String token) {
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        return response;
    }
}
