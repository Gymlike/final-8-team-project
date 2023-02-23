package com.team.final8teamproject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration

public class EmailConfig {

    @org.springframework.beans.factory.annotation.Value("${mail.smtp.port}")
    private int port;
    @org.springframework.beans.factory.annotation.Value("${mail.smtp.socketFactory.port}")
    private int socketPort;
    @org.springframework.beans.factory.annotation.Value("${mail.smtp.auth}")
    private boolean auth;
    @org.springframework.beans.factory.annotation.Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @org.springframework.beans.factory.annotation.Value("${mail.smtp.starttls.required}")
    private boolean startlls_required;
    @org.springframework.beans.factory.annotation.Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @org.springframework.beans.factory.annotation.Value("${AdminMail.id}")
    private String id;
    @org.springframework.beans.factory.annotation.Value("${AdminMail.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(id);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", socketPort);
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", starttls);
        pt.put("mail.smtp.starttls.required", startlls_required);
        pt.put("mail.smtp.socketFactory.fallback",fallback);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return pt;
    }

}