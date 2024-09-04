package com.example.fullCafe_spring_maven.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // OpenAPI 전역 설정
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Auth"))
                .components(new Components().addSecuritySchemes("Bearer Auth", securityScheme()))
                .info(new Info().title("Test Title"));
    }

    // 테스트 그룹 헬로 설정
    @Bean
    public GroupedOpenApi helloGroup(){
        return GroupedOpenApi.builder()
                .group("hello")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Hello API").version("2.6.0")))
                .pathsToMatch("/hello")
                .build();
    }

    @Bean
    public GroupedOpenApi userGroup(){
        return GroupedOpenApi.builder()
                .group("user")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("User API").version("2.6.0")))
                .pathsToMatch("/register","/user")
                .build();
    }
    @Bean
    public GroupedOpenApi reviewGroup(){
        return GroupedOpenApi.builder()
                .group("review")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Review API").version("2.6.0")))
                .pathsToMatch("/review","/reviews/**")
                .build();
    }

    @Bean
    public GroupedOpenApi visitGroup(){
        return GroupedOpenApi.builder()
                .group("visit")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Visit API").version("2.6.0")))
                .pathsToMatch("/visit","/visits/**")
                .build();
    }

    @Bean
    public GroupedOpenApi bookmarkGroup(){
        return GroupedOpenApi.builder()
                .group("bookmark")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("bookmark API").version("2.6.0")))
                .pathsToMatch("/api/bookmarks/**")
                .build();
    }

    // Scheme 정보
    @Bean
    public SecurityScheme securityScheme(){
        return new SecurityScheme()
                .name("Test")
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT");
    }

}
