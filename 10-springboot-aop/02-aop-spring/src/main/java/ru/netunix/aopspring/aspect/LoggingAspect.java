package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {

    @Before("ru.netunix.aopspring.aspect.AopPointcuts.forDaoPackageNoGettersNoSetters()")
    public void beforeAddAccountAdvice(){
        System.out.println("=====>>>>> Executing @Before advice on any method with any params");

    }


}
