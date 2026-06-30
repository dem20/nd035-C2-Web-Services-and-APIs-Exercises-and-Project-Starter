package com.udacity.bootstrap.config;
//Adjust this class to be a Swagger using a Docker Bean

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    
    
    
    @Bean
    public GroupedOpenApi dogApi() {
        return GroupedOpenApi.builder()
                .group("dog")
                .pathsToMatch("/dogs/**")
                .build();
    }
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Dog API")
                        .description("This API returns a list of dogs.")
                        .version("1.0.0"));
    }


    
}
