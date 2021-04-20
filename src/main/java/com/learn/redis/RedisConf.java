package com.learn.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConf {
	@Bean
	JedisConnectionFactory jedisConnectionFactory(@Value("${redis.hostname}") String host,@Value("${redis.port}") Integer port,@Value("${redis.port}") String  password ) {
		 RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		  redisStandaloneConfiguration.setHostName(host);
		  redisStandaloneConfiguration.setPort(port);
		  redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		  JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		  jedisClientConfiguration.connectTimeout(Duration.ofMillis(20000));
		  jedisClientConfiguration.usePooling();
		  return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(@Autowired JedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
}
