package com.team.final8teamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync(proxyTargetClass=true)
@EnableCaching(order = Ordered.HIGHEST_PRECEDENCE)
@EnableWebSecurity
public class Final8TeamProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(Final8TeamProjectApplication.class, args);

    }
}
