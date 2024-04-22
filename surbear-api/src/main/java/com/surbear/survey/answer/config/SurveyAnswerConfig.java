package com.surbear.survey.answer.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyAnswerConfig {
    @Bean
    public GroupedOpenApi getSurveyAnswerApi() {
        return GroupedOpenApi.builder()
                .group("SURVEY-ANSWER")
                .pathsToMatch("/survey/answer/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.survey.answer.controller")
                .build();
    }
}
