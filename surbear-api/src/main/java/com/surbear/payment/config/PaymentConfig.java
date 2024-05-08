package com.surbear.payment.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    public GroupedOpenApi getPaymentApi() {
        return GroupedOpenApi.builder()
                .group("상품")
                .pathsToMatch("/payment/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.payment.controller")
                .build();
    }
}