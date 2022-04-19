package com.example.demo.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.function.Supplier;

/**
 *  @description
 *  @author he.xuelong
 *  @date 2021年07月19日 16:52
 *
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.timeout}")
    private int clusterRedisTimeout;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int clusterMaxActive;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int clusterMaxWait;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int clusterMaxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int clusterMinIdle;



//    @Value("#{'${spring.redis.cluster.nodesGenYin}'.split(',')}")
//    private String[] clusterNodesGenYin;


    @Bean(name = "factory")
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(clusterMaxActive);
        poolConfig.setMaxIdle(clusterMaxIdle);
        poolConfig.setMaxWaitMillis(clusterMaxWait);
        poolConfig.setMinIdle(clusterMinIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        JedisClientConfiguration clientConfig = JedisClientConfiguration
                .builder()
                .usePooling()
                .poolConfig(poolConfig)
                .and()
                .readTimeout(Duration.ofMillis(clusterRedisTimeout))
                .build();

        // 集群redis
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
//         RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
         redisConfig.setDatabase(database);
         redisConfig.setHostName(redisHost);
         redisConfig.setPort(redisPort);
         redisConfig.setPassword(password);
//         redisConfig.clusterNode(redisHost,redisPort);
//         redisConfig.setMaxRedirects(maxRedirects);
//         redisConfig.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisConfig,clientConfig);
    }


    @Bean(name = "redisTemplate")
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisConnectionFactory factory = connectionFactory();
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "commonRedisTemplate")
    @SuppressWarnings("all")
    public <T> RedisTemplate<String, T> commonRedisTemplate() {
        RedisConnectionFactory factory = connectionFactory();
        RedisTemplate<String, T> template = new RedisTemplate<String, T>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
