package com.example.FoodieFix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodieFix.dto.AuthRequestDto;
import com.example.FoodieFix.dto.AuthResponseDto;
import com.example.FoodieFix.entity.SystemUser;
import com.example.FoodieFix.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<SystemUser> register(@Valid@RequestBody SystemUser request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDto> login(@Valid@RequestBody AuthRequestDto request) {
        return ResponseEntity.ok(service.login(request));
    }
}