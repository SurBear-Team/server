package com.surbear.external.giftishow.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GiftishowConfig {
    @Bean
    public GroupedOpenApi getGiftishowApi() {
        return GroupedOpenApi.builder()
                .group("기프티쇼")
                .pathsToMatch("/giftishow/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.external.giftishow.controller")
                .build();
    }
}
