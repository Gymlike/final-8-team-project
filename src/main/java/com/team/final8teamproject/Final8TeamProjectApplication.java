package com.team.final8teamproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableWebSocket
public class Final8TeamProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(Final8TeamProjectApplication.class, args);

    }

}
