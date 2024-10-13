package ru.netunix.thymeleafemployees.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* ru.netunix.thymeleafemployees.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* ru.netunix.thymeleafemployees.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* ru.netunix.thymeleafemployees.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAllApp() {
    }

    //add before advice
    @Before("forAllApp()")
    public void before(JoinPoint joinPoint) {
        //display method
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("====>> in @Before: calling method: " + methodName);
        //display arguments
        Arrays.stream(joinPoint.getArgs()).forEach(x -> logger.info((String) x.toString()));


    }

    @AfterReturning(
            pointcut = "forAllApp()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //display method
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("====>> in @AfterReturning: calling method: " + methodName);
        logger.info("====>> result " + result);
    }

}
