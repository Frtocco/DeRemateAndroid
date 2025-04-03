package com.example.deremate.data.api.model;

public class UserModel {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String jwt;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getJwt() {
        return jwt;
    }
}
