package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(public void add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("=====>>>>> Executing @Before advice on addAccount()");

    }
}
