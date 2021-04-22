package com.learn.redis;

import java.time.Duration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
@Component
public class RedisConf {
	@Bean
	JedisConnectionFactory jedisConnectionFactory(@Value("${redis.hostname}") String host,@Value("${redis.port}") Integer port,@Value("${redis.password}") String  password ) {
		 RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		  redisStandaloneConfiguration.setHostName(host);
		  redisStandaloneConfiguration.setPort(port);
		  redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		  JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		  jedisClientConfiguration.connectTimeout(Duration.ofMillis(20000));
		 GenericObjectPoolConfig<JedisPoolConfig> jPoolConfig=new GenericObjectPoolConfig<JedisPoolConfig>();
		  jPoolConfig.setMaxTotal(2000);
		  jPoolConfig.setMinIdle(20); 
		  jedisClientConfiguration.usePooling().poolConfig(jPoolConfig); 
		  return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(@Autowired JedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
}
