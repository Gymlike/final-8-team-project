package com.team.final8teamproject.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Slf4j
@Aspect
@Component
public class ExecutionTimer {
    // 조인포인트를 어노테이션으로 설정
    @Pointcut("@annotation(com.team.final8teamproject.aop.Timer)")
    private void timer(){}

    // 패키지의 Controller 라는 이름으로 끝나는 패키지에 모두 적용 (매서드만)
    @Pointcut("within(*..*Controller)")
    private void cut(){}
    // 메서드 전 후로 시간 측정 시작하고 멈추려면 Before,
    // AfterReturning으로는 시간을 공유 할 수가 없음 Around 사용!
    @Around("timer()")
    public Object AssumeExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object proceed = joinPoint.proceed();// 조인포인트의 메서드 실행
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        log.info("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);
        return proceed;
    }
}