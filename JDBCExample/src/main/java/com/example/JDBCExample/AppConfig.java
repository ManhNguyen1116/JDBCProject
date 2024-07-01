package com.example.JDBCExample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public AllLogging alllogging() {
		return new AllLogging();
	}
	
	@Bean
	public MyAspect myAspect() {
		return new MyAspect();
	}
	
	@Bean
	public App app() {
		return new App();
	}
}