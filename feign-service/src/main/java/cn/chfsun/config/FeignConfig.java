package cn.chfsun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {
	@Bean
	Logger.Level level() {
		return Logger.Level.FULL;
	}
}
