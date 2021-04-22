package com.learn.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
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
		

	    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
	    poolConfig.setMaxIdle(15);
	    poolConfig.setMinIdle(5);
	    poolConfig.setMaxWaitMillis(3000);
	    poolConfig.setMaxTotal(50);

	    LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
	            .commandTimeout(Duration.ofSeconds(10))
	            .shutdownTimeout(Duration.ZERO)
	            .poolConfig(poolConfig)
	            .build();

	    LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
	    lettuceConnectionFactory.setShareNativeConnection(false);

	    return lettuceConnectionFactory;
	}

}
