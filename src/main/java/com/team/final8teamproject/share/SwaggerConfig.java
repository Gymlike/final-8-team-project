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
                .group("t_exercise")
                .pathsToMatch("/t-exercise/**")
                .build();
    }
}
