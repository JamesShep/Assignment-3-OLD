package com.coderscampus.assignment3;

public class UserLoginApplication {

    public static void main(String[] args) {

        UserService userService = new UserService();
        userService.retrieveUser();
        userService.validateLogin();
    }
}