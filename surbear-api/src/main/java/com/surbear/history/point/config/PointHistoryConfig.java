package com.surbear.history.point.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class PointHistoryConfig {

    @Bean
    public GroupedOpenApi getPointHistoryApi() {
        return GroupedOpenApi.builder()
                .group("포인트 기록")
                .pathsToMatch("/history/point/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.history.point.controller")
                .build();
    }
}