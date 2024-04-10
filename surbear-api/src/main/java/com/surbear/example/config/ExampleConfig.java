package com.surbear.example.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfig {
    @Bean
    public GroupedOpenApi getLoginApi() {
        return GroupedOpenApi.builder()
                .group("EXAMPLE")
                .pathsToMatch("/example/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.example.controller")
                .build();
    }
}
