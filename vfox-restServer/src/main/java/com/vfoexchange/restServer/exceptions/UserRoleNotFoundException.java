package com.vfoexchange.restServer.exceptions;

public class UserRoleNotFoundException extends RuntimeException{

    public UserRoleNotFoundException(String role) {
        super("could not find user '" + role + ".");
    }
}