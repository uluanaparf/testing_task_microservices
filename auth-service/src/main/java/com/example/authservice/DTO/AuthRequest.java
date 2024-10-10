package com.example.authservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public class AuthRequest {

    @NotNull(message = "Username can't be null")
    @UniqueElements
    @Schema(description = "Имя пользователя", example = "Vadim12")
    private String username;

    @NotNull(message = "Password can't be null")
    @UniqueElements
    @Schema(description = "Пароль", example = "12345678")
    private String password;


    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public AuthRequest() {}

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
