package com.surbear.common.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthorizationConfig implements WebMvcConfigurer {

    private final AuthorizationArgumentResolver authorizationArgumentResolver;

    public AuthorizationConfig(AuthorizationArgumentResolver authorizationArgumentResolver) {
        this.authorizationArgumentResolver = authorizationArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizationArgumentResolver);
    }
}
