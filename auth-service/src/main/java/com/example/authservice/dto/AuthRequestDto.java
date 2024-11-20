package com.example.authservice.dto;

import jakarta.validation.constraints.NotNull;

public class AuthRequestDto {

    @NotNull(message = "Username can't be null")
    private String username;

    @NotNull(message = "Password can't be null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}