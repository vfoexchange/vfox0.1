package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.model.Mail;
import com.vfoexchange.restServer.service.EmailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service("emailServices")
public class EmailServicesImpl implements EmailServices {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailServicesImpl.class);
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(final Mail mail) {
        LOGGER.info("Sending  Email");
        MimeMessage msg = emailSender.createMimeMessage();
        try {
            msg.setSubject(mail.getSubject());
            msg.setRecipients(MimeMessage.RecipientType.TO, mail.getTo());
            msg.setContent(mail.getContent(), "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            emailSender.send(msg);
        } catch (MessagingException ex) {
            LOGGER.error("Error in sending mail " + ex.getMessage());
        }
    }
}
