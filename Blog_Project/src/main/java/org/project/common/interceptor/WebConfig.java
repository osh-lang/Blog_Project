package org.project.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @Class Name : WebConfig.java
* @Description : 세션 처리를 위한 url 패턴 설정 클래스를 정의한다.
* @since 2020.12.21
* @version 1.0
* @see
*/

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public SessionInterceptor sessionInterceptor() {
		return new SessionInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**")
				.excludePathPatterns("/login/**")
				.excludePathPatterns("/join/**")
				.excludePathPatterns("/auth/**")
				.excludePathPatterns("/atchFile/**")
				.excludePathPatterns("/api/v1/login/**")
				.excludePathPatterns("/api/v1/snsJoin/**")
				.excludePathPatterns("/api/v1/join/userJoin");
	}
}
