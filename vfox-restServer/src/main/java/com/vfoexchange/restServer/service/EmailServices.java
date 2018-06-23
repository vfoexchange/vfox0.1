package com.vfoexchange.restServer.service;

import com.vfoexchange.restServer.model.Mail;

/*
Send an email through Amazon SES using the AWS SDK for Java
 */
public interface EmailServices {

    /*
    Method for send email
     */
    public void sendMail(Mail mail) throws Exception;
}
