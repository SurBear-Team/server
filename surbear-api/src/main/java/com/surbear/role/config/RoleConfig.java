package com.surbear.role.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {
    @Bean
    public GroupedOpenApi getRoleApi() {
        return GroupedOpenApi.builder()
                .group("권한")
                .pathsToMatch("/role/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.role.controller")
                .build();
    }
}