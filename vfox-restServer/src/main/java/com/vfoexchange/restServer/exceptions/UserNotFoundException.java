package com.vfoexchange.restServer.exceptions;

/*
Add custom exception class for  for user  not found
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("could not find user '" + username + ".");
    }
}
