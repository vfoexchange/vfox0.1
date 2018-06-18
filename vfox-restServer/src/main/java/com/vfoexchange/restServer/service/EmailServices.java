package com.vfoexchange.restServer.service;

/*
Send an email through Amazon SES using the AWS SDK for Java
 */
public interface EmailServices {

    /*
    Method for send email
     */
    public void sendMail(String toEmailId) throws Exception;
}
