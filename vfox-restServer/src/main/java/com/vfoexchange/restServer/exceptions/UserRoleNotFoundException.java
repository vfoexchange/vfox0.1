package com.vfoexchange.restServer.exceptions;

/*
Add custom exception class for  for user role not found
 */
public class UserRoleNotFoundException extends RuntimeException{

    public UserRoleNotFoundException(String role) {
        super("could not find user '" + role + ".");
    }
}