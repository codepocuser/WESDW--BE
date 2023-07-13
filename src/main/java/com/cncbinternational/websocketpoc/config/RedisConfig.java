package com.cncbinternational.websocketpoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {


    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisUserPassword;


    @Bean
    public LettuceConnectionFactory redisStandAloneConnectionFactory() {
        RedisStandaloneConfiguration connection = new RedisStandaloneConfiguration();
        connection.setHostName(redisHost);
        connection.setPort(redisPort);
        connection.setUsername(redisUsername);
        connection.setPassword(redisUserPassword);
        return new LettuceConnectionFactory(connection);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisStandAloneConnectionFactory());
        return template;
    }
}