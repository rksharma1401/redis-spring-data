package com.learn.redis.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@RedisHash("AppInfo")
@Data
public class AppInfo implements Serializable {
 
	private static final long serialVersionUID = -5689994389034239816L;
	@Id
	private Integer id;
	@Indexed
	private String appName;
	private String feature;
}
