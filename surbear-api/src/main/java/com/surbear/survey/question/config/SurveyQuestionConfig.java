package com.surbear.survey.question.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyQuestionConfig {
    @Bean
    public GroupedOpenApi getSurveyQuestionApi() {
        return GroupedOpenApi.builder()
                .group("SURVEY-QUESTION")
                .pathsToMatch("/survey/question/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.survey.question.controller")
                .build();
    }
}