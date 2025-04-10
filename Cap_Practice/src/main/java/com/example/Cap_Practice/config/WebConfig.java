package com.example.Cap_Practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로 허용
                .allowedOrigins("http://localhost:3000") // React dev 서버 주소
                .allowedMethods("*") // GET, POST, PUT 등 전부 허용
                .allowedHeaders("*") // 헤더도 전부 허용
                .allowCredentials(true); // 쿠키 같은 것도 허용할지 여부
    }
}
