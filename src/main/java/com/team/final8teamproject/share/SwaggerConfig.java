package com.team.final8teamproject.share;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "SearchGYM",
        description = "SearchGYM API 명세",
        version = "v1")
)

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi texe(){
        return GroupedOpenApi.builder()
                .group("오운완게시판")
                .pathsToMatch("/t-exercise/**")
                .build();
    }

    @Bean
    public GroupedOpenApi todayMeal(){
        return GroupedOpenApi.builder()
                .group("오늘식단게시판")
                .pathsToMatch("/todaymeal/**")
                .build();
    }
}
