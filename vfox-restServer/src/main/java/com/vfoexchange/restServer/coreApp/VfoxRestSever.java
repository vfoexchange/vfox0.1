package com.vfoexchange.restServer.coreApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vfoexchange"})
@PropertySources({@PropertySource(value = "classpath:application.properties")})
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
public class VfoxRestSever {

    public static void main(String[] args) {
        SpringApplication.run(VfoxRestSever.class, args);
    }
}
