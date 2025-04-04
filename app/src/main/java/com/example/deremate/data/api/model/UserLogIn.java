package com.example.deremate.data.api.model;

public class UserLogIn {
    private String username;
    private String password;

    public UserLogIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
