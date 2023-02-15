package com.team.final8teamproject.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.final8teamproject.security.dto.SecurityExceptionDto;
import com.team.final8teamproject.security.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        if(token != null) {
            //JwtUtil 클래스 메소드인 vaildateToken에서 토큰을 검사한다.
            //토큰에 문제가 있을때 if문을 실행시킨다.
            if (!jwtUtil.validateToken(token, response)) {
                jwtExceptionHandler(response, "Invalid JWT signature", HttpStatus.BAD_REQUEST.value());
                return;
            }
            boolean refreshToken = redisUtil.hasKeyBlackList(token);
            if(refreshToken){
                throw new IllegalArgumentException("Please Login again.");
            }
            /**
             여기서 관리자인지 유저인지 확인한다음에..
             setAuthentication을 해줘야한다...
             어떤게 권한을 확인하고 보내줄것인가? 답없는데..??
             합쳐서 해야하나? 나눠서 해야하나??
             한 데이터베이스에 넣어두지 않을탠데..
             */
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        //filterChain은 체인의 다음 필터를 호출하거나 호출 필터가 체인의 마지막 필터인 경우 체인 끝에 리소스를 호출합니다.
        filterChain.doFilter(request,response);
    }

    //SecurityContextHolder에 관한 사이트
    // https://00hongjun.github.io/spring-security/securitycontextholder/
    //
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        //jwtUtil에서  username에 알맞은 User객체를 가져와서 Authentication에 넣어준다.
        Authentication authentication = jwtUtil.createUserAuthentication(username);

        //그리고 빈 컨텍스트에 가져온 데이터(User객체와, username) authentication변수를 넣어주고
        context.setAuthentication(authentication);
        //누가 인증하였는지에 대한 정보들을 저장한다.
        SecurityContextHolder.setContext(context);
    }
    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}