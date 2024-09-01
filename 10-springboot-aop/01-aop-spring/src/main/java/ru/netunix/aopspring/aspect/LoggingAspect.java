package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    //strict by return type  strcit by packege name any class any method
    @Before("execution(void ru.netunix.aopspring.dao.*.*())")
    public void beforeAddAccountAdvice(){
        System.out.println("=====>>>>> Executing @Before advice on any method  no params in our package");

    }
    //any parameter
//    @Before("execution(* ru.netunix..add*(..))")
//    public void beforeAddAccountAdviceWithParam(){
//        System.out.println("=====>>>>> Executing @Before advice on * add*(Account) - with parameter");
//
//    }
}
