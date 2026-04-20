package com.yettel.customermanagement.customermanagement.config;

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
                        .title("Customer API")
                        .version("v1")
                        .description("Customer Management Service API"));
    }
}