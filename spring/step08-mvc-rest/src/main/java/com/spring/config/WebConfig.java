package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")		//모든 경로에 대해 CORS 허용
				.allowedOrigins("*")	//모든 출처 허용(실제 운영에서는 특정
				.allowedMethods("*")	//모든 HTTP 메서드 허용
				.allowedHeaders("*");
		
	}

	
}
