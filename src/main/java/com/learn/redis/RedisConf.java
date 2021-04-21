package com.learn.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisConf {
	@Bean
	LettuceConnectionFactory lettuceConnectionFactory(@Value("${redis.hostname}") String host,
			@Value("${redis.port}") Integer port, @Value("${redis.password}") String password) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		
		
		LettuceClientConfigurationBuilder lettuce = LettuceClientConfiguration
				.builder();
		lettuce.commandTimeout(Duration.ofSeconds(30l)); 	
		
		return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuce.build());
	}

}
