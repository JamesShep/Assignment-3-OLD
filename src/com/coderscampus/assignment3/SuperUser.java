package com.coderscampus.assignment3;

public class SuperUser extends User {

    public SuperUser (String email, String password, String name, String role) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setRole(role);
    }

}
