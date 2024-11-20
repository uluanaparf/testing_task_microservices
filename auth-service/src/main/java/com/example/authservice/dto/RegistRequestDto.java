package com.example.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistRequestDto {

    @NotNull(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters long")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    public @NotNull(message = "Username is required") @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username is required") @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters") String username) {
        this.username = username;
    }

    public @NotNull(message = "Password is required") @Size(min = 8, max = 100, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password is required") @Size(min = 8, max = 100, message = "Password must be at least 8 characters long") String password) {
        this.password = password;
    }

    public @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") String email) {
        this.email = email;
    }
}
