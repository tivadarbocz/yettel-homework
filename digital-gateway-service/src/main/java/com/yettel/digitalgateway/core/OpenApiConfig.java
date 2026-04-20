package com.yettel.digitalgateway.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Gateway API")
                        .version("v1")
                        .description("Digital Gateway Service API"));
    }
}