package com.vfoexchange.restServer.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("could not find user '" + username + ".");
    }
}
