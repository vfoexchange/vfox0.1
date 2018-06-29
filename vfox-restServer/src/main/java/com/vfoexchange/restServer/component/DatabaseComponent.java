package com.vfoexchange.restServer.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DatabaseComponent {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
