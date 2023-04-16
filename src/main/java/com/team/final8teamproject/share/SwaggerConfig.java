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
    @Bean
    public GroupedOpenApi freeBoard(){
        return GroupedOpenApi.builder()
                .group("자유게시판")
                .pathsToMatch("/freeboard/**")
                .build();
    }
    @Bean
    public GroupedOpenApi contact(){
        return GroupedOpenApi.builder()
                .group("문의게시판")
                .pathsToMatch("/api/contact/**")
                .build();
    }
    @Bean
    public GroupedOpenApi faq(){
        return GroupedOpenApi.builder()
                .group("FAQ")
                .pathsToMatch("/api/faqs/**")
                .build();
    }
    @Bean
    public GroupedOpenApi managers(){
        return GroupedOpenApi.builder()
                .group("공지사항")
                .pathsToMatch("/api/managers/**")
                .build();
    }
    @Bean
    public GroupedOpenApi gym(){
        return GroupedOpenApi.builder()
                .group("헬스장조회")
                .pathsToMatch("/api/gym/**")
                .build();
    }
    @Bean
    public GroupedOpenApi gymreview(){
        return GroupedOpenApi.builder()
                .group("헬스장리뷰")
                .pathsToMatch("/api/gymreview/**")
                .build();
    }
    @Bean
    public GroupedOpenApi trainers(){
        return GroupedOpenApi.builder()
                .group("트레이너")
                .pathsToMatch("/api/trainers/**")
                .build();
    }
    @Bean
    public GroupedOpenApi user(){
        return GroupedOpenApi.builder()
                .group("유저")
                .pathsToMatch("/api/user/**")
                .build();
    }
}
