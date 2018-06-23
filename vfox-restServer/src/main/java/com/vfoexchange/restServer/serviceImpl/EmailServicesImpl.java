package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.model.Mail;
import com.vfoexchange.restServer.service.EmailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service("emailServices")
public class EmailServicesImpl implements EmailServices {

    @Autowired
    private JavaMailSender emailSender;

    private static Logger log = LoggerFactory.getLogger(EmailServicesImpl.class);
    @Override
    public void sendMail(final Mail mail) {
        log.info("Mail - Sending  Email for verify ");
        MimeMessage msg = emailSender.createMimeMessage();

        try{
            msg.setSubject(mail.getSubject());
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail.getTo(), false));
            msg.setContent(mail.getContent(), "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            emailSender.send(msg);


        }catch(MessagingException  ex ){
            System.out.print("Error in sending mail "+ex.getMessage());
        }

    }
}
