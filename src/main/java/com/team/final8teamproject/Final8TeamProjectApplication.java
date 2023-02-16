package com.team.final8teamproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class Final8TeamProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Final8TeamProjectApplication.class, args);

    }

}
