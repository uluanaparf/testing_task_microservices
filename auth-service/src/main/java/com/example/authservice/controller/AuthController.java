package com.example.authservice.controller;

import com.example.authservice.DTO.AuthRequest;
import com.example.authservice.model.User;
import com.example.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String registerUser(@RequestBody User user) {
        return authService.saveUser(user);
    }

    @Tag(name="Пользователи",description ="Авторизация и регистрация пользователя" )
    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет получить пользователю токен"
    )
    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else{
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Tag(name="Валидация",description ="Проверка токена на валидность (активность)")
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return " Token validated successfully ";
    }
}