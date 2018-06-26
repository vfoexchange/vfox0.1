package com.vfoexchange.restServer.coreApp;

import javax.sql.DataSource;
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

import java.util.Properties;

/*
Database connection using DataSource and JdbcTemplate
with configurable database properties
 */
@Configuration
@ComponentScan(basePackages = "com.vfoexchange")
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        mailProperties.put("mail.smtp.starttls.enable",  env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setProtocol(env.getProperty("spring.mail.protocol"));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
        return mailSender;
    }

}
