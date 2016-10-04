package org.seckill.configuration;

import org.seckill.dao.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisDaoConfig {

	@Autowired
	Environment env;

	@Bean
	public RedisDao redis() {
		return new RedisDao(env.getProperty("redis.ip"), Integer.valueOf(env.getProperty("redis.port")));
	}
}
