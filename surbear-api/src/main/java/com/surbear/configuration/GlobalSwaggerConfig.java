package com.surbear.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class GlobalSwaggerConfig {

    @Value("${springdoc.url}")
    private String url;
    private String localUrl = "http://localhost:8080";
    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("https");

        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("http/localhost");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("surbear-api")
                        .description("서베어 서버 명세서")
                        .version("1.0"))
                .servers(Arrays.asList(server,localServer))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }


}
