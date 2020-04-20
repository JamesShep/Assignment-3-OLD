package com.coderscampus.assignment3;

public class NormalUser extends User {

    public NormalUser (String email, String password, String name, String role ) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setRole(role);
    }

}