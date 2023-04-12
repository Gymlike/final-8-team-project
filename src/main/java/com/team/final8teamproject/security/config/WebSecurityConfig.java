package com.team.final8teamproject.security.config;

import com.team.final8teamproject.security.exception.CustomAccessDeniedHandler;
import com.team.final8teamproject.security.exception.CustomAuthenticationEntryPoint;
import com.team.final8teamproject.security.jwt.JwtAuthFilter;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableMethodSecurity // 위 어노테이션은 Deprecated
@EnableScheduling // @Scheduled 어노테이션 활성화
public class WebSecurityConfig implements WebMvcConfigurer {
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 가장 먼저 시큐리티를 사용하기 위해선 선언해준다.

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers("/actuator/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 필터를 타고 세션이 아닌 jwt방식을 타고 권한을
     * 확인하는 메소드
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests()

        http.authorizeHttpRequests()//요청에 대한 권한을 지정할 수 있다.
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/actuator").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/owner/**").permitAll()
                .requestMatchers("/owner/**").hasAnyRole("Owner", "Manager", "GeneralManager")
                .requestMatchers("/api/general/**").hasRole("GeneralManager")
                .requestMatchers("/h2-console").permitAll()
                .requestMatchers("/todaymeal/allboard").permitAll()
                .requestMatchers("/freeboard/allboard").permitAll()
                .requestMatchers("/t-exercise/allboard").permitAll()
                .requestMatchers("/t-exercise/top3").permitAll()
                .requestMatchers("/todaymeal/top3").permitAll()
                .requestMatchers("/freeboard/top3").permitAll()
                .requestMatchers("/t-exercise/selectboard/**").permitAll()
                .requestMatchers("/todaymeal/selectboard/**").permitAll()
                .requestMatchers("/freeboard/selectboard/**").permitAll()
                .requestMatchers("/api/user/kakao/callback").permitAll()
                .requestMatchers("/api/profile/kakao").permitAll()
                .requestMatchers("/api/home").permitAll()
                .requestMatchers("/api/company/**").permitAll()
                .requestMatchers("/api/user/find/**").permitAll()
                .requestMatchers("/api/faqs/check/**").permitAll()
                .requestMatchers("/api/contact/inquiries/**").permitAll()
                .requestMatchers("/api/managers/notices/check/**").permitAll()
                .requestMatchers("/api/owners").permitAll()
                .requestMatchers("/api/trainers").permitAll()
                .requestMatchers("/api/validate").permitAll()
                .requestMatchers("/api/user/email/**").permitAll()
                .requestMatchers("/hello").permitAll()
                .requestMatchers("/api/managers/notices").hasRole("Manager")
                .requestMatchers("/api/faqs").hasRole("Manager")

                .anyRequest().authenticated()//인증이 되어야 한다는 이야기이다.
                .and()
                // JWT 인증/인가를 사용하기 위한 설정
                .addFilterBefore(new JwtAuthFilter(jwtUtil, redisUtil),
                        UsernamePasswordAuthenticationFilter.class);
        // xss 공격을 막기위한 필터 설정
//            .addFilterBefore(new XssEscapeFilter(xssEscapeUtil), CsrfFilter.class)

        // 401 Error 처리, Authorization 즉, 인증과정에서 실패할 시 처리
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        // 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
                .allowedOriginPatterns("*")
                .exposedHeaders("Authorization");
    }


  /*
  만약 swagger로 web에서 API를 관리하고 싶을때 추가
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/2.6.1/");

    }
   */
}