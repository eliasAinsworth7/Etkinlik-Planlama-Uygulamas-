package com.alpr.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpr.project.dto.AuthResponse;
import com.alpr.project.dto.LoginRequest;
import com.alpr.project.dto.RegisterRequest;
import com.alpr.project.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    

    private final AuthService authService;

    @PostMapping("/register")
    public String postMethodName(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public AuthResponse postMethodName(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
    
}
