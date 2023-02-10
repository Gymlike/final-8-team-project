package com.team.final8teamproject.security.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;
import java.io.IOException;
@Slf4j
@Component
public class LocalRedisConfig {
    private RedisServer redisServer;
    @Value("${spring.data.redis.port}")
    private int port;
    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(port);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

}