package com.example.authservice.dto;

public class AuthResponseDto {
    private String token;

    public AuthResponseDto() {}

    public AuthResponseDto(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
