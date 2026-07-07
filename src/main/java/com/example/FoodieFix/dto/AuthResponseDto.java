package com.example.FoodieFix.dto;

import com.example.FoodieFix.entity.SystemUser.UserRole;

public class AuthResponseDto {

    private String token;
    private String username;
    private UserRole role;
    private String fullName;

    public AuthResponseDto(String token, String username, UserRole role, String fullName) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}