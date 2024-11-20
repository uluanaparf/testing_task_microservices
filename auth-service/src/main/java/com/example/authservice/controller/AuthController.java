package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequestDto;
import com.example.authservice.dto.AuthResponseDto;
import com.example.authservice.dto.RegistRequestDto;
import com.example.authservice.dto.RegistResponseDto;
import com.example.authservice.model.User;
import com.example.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Tag(name="Пользователи",description ="Авторизация и регистрация пользователя" )
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    @PostMapping("/register")
    public ResponseEntity<RegistResponseDto> registerUser(@Valid @RequestBody RegistRequestDto registRequestDto) {
        RegistResponseDto responseDto = authService.saveUser(registRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Tag(name="Пользователи",description ="Авторизация и регистрация пользователя" )
    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет получить пользователю токен"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> getToken( @Valid @RequestBody AuthRequestDto authRequest) {
       AuthResponseDto responseDto = authService.generateToken(authRequest);
       return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Tag(name="Валидация",description ="Проверка токена на валидность (активность)")
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        String validMessage = authService.validateToken(token);
        return new ResponseEntity<>(validMessage, HttpStatus.OK);
    }
}