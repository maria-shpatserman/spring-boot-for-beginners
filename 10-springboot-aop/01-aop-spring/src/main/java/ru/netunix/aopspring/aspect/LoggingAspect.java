package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    //strict by return type and no params
    @Before("execution(void add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("=====>>>>> Executing @Before advice on addAccount()");

    }
    @Before("execution(* add*(ru.netunix.aopspring.entity.Account,..))")
    public void beforeAddAccountAdviceWithParam(){
        System.out.println("=====>>>>> Executing @Before advice on * add*(Account) - with parameter");

    }
}
