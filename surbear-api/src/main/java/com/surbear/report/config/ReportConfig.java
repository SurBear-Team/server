package com.surbear.report.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfig {
    @Bean
    public GroupedOpenApi getReportApi() {
        return GroupedOpenApi.builder()
                .group("신고")
                .pathsToMatch("/report/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.report.controller")
                .build();
    }
}

