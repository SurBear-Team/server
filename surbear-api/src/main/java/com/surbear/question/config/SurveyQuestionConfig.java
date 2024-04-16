package com.surbear.question.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyQuestionConfig {
    @Bean
    public GroupedOpenApi getLoginApi() {
        return GroupedOpenApi.builder()
                .group("SURVEY-QUESTION")
                .pathsToMatch("/question/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.question.controller")
                .build();
    }
}