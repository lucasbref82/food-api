package br.com.foodapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.foodapi.interceptor.LoggerInterceptor;

@Configuration
public class LoggerConfig implements WebMvcConfigurer{
	@Autowired 
	private LoggerInterceptor logerInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(logerInterceptor)
		.addPathPatterns("/**");
		
	}
}
