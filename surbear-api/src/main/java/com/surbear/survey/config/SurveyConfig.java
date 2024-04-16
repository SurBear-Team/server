package com.surbear.survey.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyConfig {
    @Bean
    public GroupedOpenApi getLoginApi() {
        return GroupedOpenApi.builder()
                .group("SURVEY")
                .pathsToMatch("/survey/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.survey.controller")
                .build();
    }
}
