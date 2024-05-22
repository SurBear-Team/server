package com.surbear.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000"
                        , "http://localhost:8080"
                        , "http://3.37.53.15:3000"
                        , "http://3.37.53.15:8080"
                        , "https://api.surbear.site:443"
                        , "https://app.surbear.site"
                        , "http://surbear.s3-website.ap-northeast-2.amazonaws.com"
                        , "https://surbear.s3-website.ap-northeast-2.amazonaws.com")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
