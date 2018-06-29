package com.vfoexchange.restServer.coreApp;

import javax.sql.DataSource;

import com.vfoexchange.restServer.component.DatabaseComponent;
import com.vfoexchange.restServer.component.MailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

/*
Database connection using DataSource and JdbcTemplate
with configurable database properties
 */
@Configuration
@ComponentScan(basePackages = "com.vfoexchange")
public class DbConfig {

    @Autowired
    private DatabaseComponent database;
    @Autowired
    MailComponent mailComponent;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(database.getDriverClassName());
        dataSource.setUrl(database.getUrl());
        dataSource.setUsername(database.getUsername());
        dataSource.setPassword(database.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", mailComponent.getAuth());
        mailProperties.put("mail.smtp.starttls.enable", mailComponent.getStarttlsEnable());
        mailProperties.put("mail.smtp.socketFactory.class", mailComponent.getSocketFactoryClass());

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(mailComponent.getHostName());
        mailSender.setPort(Integer.parseInt(mailComponent.getPortNumber()));
        mailSender.setProtocol(mailComponent.getProtocolName());
        mailSender.setUsername(mailComponent.getUsername());
        mailSender.setPassword(mailComponent.getPassword());

        return mailSender;
    }

}
