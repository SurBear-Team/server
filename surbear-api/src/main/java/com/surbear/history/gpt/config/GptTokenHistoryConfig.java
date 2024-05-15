package com.surbear.history.gpt.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GptTokenHistoryConfig {
    @Bean
    public GroupedOpenApi getGptTokenHistory() {
        return GroupedOpenApi.builder()
                .group("지피티 토큰 사용량")
                .pathsToMatch("/history/gpt/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.history.gpt.controller")
                .build();
    }
}
