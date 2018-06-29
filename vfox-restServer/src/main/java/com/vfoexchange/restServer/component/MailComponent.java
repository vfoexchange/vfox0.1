package com.vfoexchange.restServer.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Value("${spring.mail.host}")
    private String hostName;
    @Value("${spring.mail.port}")
    private String portNumber;
    @Value("${spring.mail.protocol}")
    private String protocolName;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttlsEnable;
    @Value("${spring.mail.smtp.socketFactory.class}")
    private String socketFactoryClass;

    public String getHostName() {
        return hostName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuth() {
        return auth;
    }

    public String getStarttlsEnable() {
        return starttlsEnable;
    }

    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }
}
