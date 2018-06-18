package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.service.EmailServices;
import com.vfoexchange.restServer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service("emailServices")
public class EmailServicesImpl implements EmailServices {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailServicesImpl.class);
    @Autowired
    private Environment environment;

    @Override
    public void sendMail(final String toEmailId) throws Exception {

        LOGGER.info("Mail - Sending  Email for verify ");

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        try {
            msg.setFrom(new InternetAddress(environment.getProperty("mail.smtp.from"), environment.getProperty("mail.smtp.fromname")));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId));
            msg.setSubject("VFOX Verify Email");
            msg.setContent(AppUtil.getMailBody(AppUtil.getURL(AppUtil.getEncodedString(toEmailId))), "text/html");

            transport = session.getTransport();
            transport.connect(environment.getProperty("mail.smtp.host"), environment.getProperty("mail.smtp.username"), environment.getProperty("mail.smtp.password"));
            transport.sendMessage(msg, msg.getAllRecipients());
            LOGGER.info("Mail has been sent for verify ");
        } catch (UnsupportedEncodingException | MessagingException ex) {
            LOGGER.error("Error in sending mail " + ex.getMessage());
        } finally {
            transport.close();
        }
    }
}
