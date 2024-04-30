package com.surbear.history.deletion.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForcedDeletionHistoryConfig {

    @Bean
    public GroupedOpenApi getDeletionHistoryApi() {
        return GroupedOpenApi.builder()
                .group("강제삭제")
                .pathsToMatch("/history/deletion/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.history.deletion.controller")
                .build();
    }
}
