package com.team.final8teamproject.redis.cache;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class CacheConfiguration {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, ResourceLoader resourceLoader) {
        RedisCacheConfiguration defaultConfig
                = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())
                );


        Map<String, RedisCacheConfiguration> redisCacheConfigMap
                = new HashMap<>();

        redisCacheConfigMap.put(
                CacheNames.USERBYUSERNAME,
                defaultConfig.entryTtl(Duration.ofHours(4))
        );

        // ALLUSERS에 대해서만 다른 Serializer 적용
        redisCacheConfigMap.put(
                CacheNames.ALLUSERS,
                defaultConfig.entryTtl(Duration.ofHours(4))
                        .serializeValuesWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new JdkSerializationRedisSerializer())
                        )
        );
        redisCacheConfigMap.put(
                CacheNames.LOGINUSER,
                defaultConfig.entryTtl(Duration.ofHours(2))
        );

        redisCacheConfigMap.put(
                CacheNames.SELECTEXCERISE,
                defaultConfig.entryTtl(Duration.ofHours(2))
        );
        redisCacheConfigMap.put(
                CacheNames.ALLEXCERCIES,
                defaultConfig.entryTtl(Duration.ofHours(2))
        );
        redisCacheConfigMap.put(
                CacheNames.SELECTGYMBOARD,
                defaultConfig.entryTtl(Duration.ofHours(2))
        );
        redisCacheConfigMap.put(
                CacheNames.ALLGYMBOARD,
                defaultConfig.entryTtl(Duration.ofHours(2))
        );
        redisCacheConfigMap.put(
                CacheNames.THREAD,
                defaultConfig.entryTtl(Duration.ofHours(4))
        );
        redisCacheConfigMap.put(
                CacheNames.CHANNELS,
                defaultConfig.entryTtl(Duration.ofHours(4))
        );
        //전체조회시
        redisCacheConfigMap.put(
                CacheNames.GETBOARD,
                defaultConfig.entryTtl(Duration.ofHours(4))
                        .serializeValuesWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new JdkSerializationRedisSerializer(resourceLoader.getClassLoader()))
                        )
        );

        return RedisCacheManager.builder(connectionFactory)
                .withInitialCacheConfigurations(redisCacheConfigMap)
                .build();
    }

}
