package com.devthalys.electronicpoint.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found in data base.");
    }
}
