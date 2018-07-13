package com.vfoexchange.restServer.exceptions;

public class UserAlreadyActiveException extends RuntimeException{
    public UserAlreadyActiveException(String username) {
        super("The username address you have entered is already verified "+ username );
    }
}
