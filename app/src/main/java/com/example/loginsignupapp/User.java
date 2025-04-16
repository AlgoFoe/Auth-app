package com.example.loginsignupapp;

public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return username + "," + email + "," + password;
    }

    public static User fromString(String userString) {
        String[] parts = userString.split(",");
        if (parts.length >= 3) {
            return new User(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}