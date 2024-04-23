package com.surbear.survey.question.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyQuestionConfig {
    @Bean
    public GroupedOpenApi getSurveyApi() {
        return GroupedOpenApi.builder()
                .group("설문")
                .pathsToMatch("/survey/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.survey.question.controller","com.surbear.survey.answer.controller")
                .build();
    }
}