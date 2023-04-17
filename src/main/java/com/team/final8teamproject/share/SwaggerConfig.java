package com.team.final8teamproject.share;




import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title(" Search GYM ")
                .description(" Search GYM API ");

        // SecuritySecheme명
        String jwtSchemeName = "jwtAuth";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
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
