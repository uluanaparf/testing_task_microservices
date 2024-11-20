package com.example.authservice.service;

import com.example.authservice.dto.AuthRequestDto;
import com.example.authservice.dto.AuthResponseDto;
import com.example.authservice.dto.RegistRequestDto;
import com.example.authservice.dto.RegistResponseDto;
import com.example.authservice.mapper.AuthMapper;
import com.example.authservice.mapper.RegistMapper;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private RegistMapper registMapper;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public RegistResponseDto saveUser(RegistRequestDto registRequestDto) {
        if (userRepository.existsByUsername(registRequestDto.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }
        User user = registMapper.toEntity(registRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        RegistResponseDto responseDto = new RegistResponseDto();
        responseDto.setMessage("User successfully registered");
        return responseDto;
    }

    @Transactional
    public AuthResponseDto generateToken(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()) );
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = jwtService.generateToken(authRequestDto.getUsername());
        return authMapper.toResponseDto(token);
    }

    public String validateToken(String token){
        try{
            jwtService.validateToken(token);
            return "Token is valid";
        } catch (RuntimeException e){
            return "Invalid token";
        }

    }
}