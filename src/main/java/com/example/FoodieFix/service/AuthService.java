package com.example.FoodieFix.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.FoodieFix.dto.AuthRequestDto;
import com.example.FoodieFix.dto.AuthResponseDto;
import com.example.FoodieFix.entity.SystemUser;
import com.example.FoodieFix.exception.UnauthorizedException;
import com.example.FoodieFix.repository.SystemUserRepository;
import com.example.FoodieFix.util.JwtUtil;

@Service
public class AuthService {

    private final SystemUserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(SystemUserRepository repo, PasswordEncoder encoder,
                        AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public SystemUser register(SystemUser request) {
        request.setPassword(encoder.encode(request.getPassword()));
        return repo.save(request);
    }

    public AuthResponseDto login(AuthRequestDto request) {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid username or password");
        }

        if (!auth.isAuthenticated()) {
            throw new UnauthorizedException("Invalid username or password");
        }

        SystemUser user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponseDto(token, user.getUsername(), user.getRole(), user.getFullname());
    }
}