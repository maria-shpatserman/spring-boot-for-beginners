package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    //apply to all method in all classes in DAO package
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.*(..))")
    private void forDaoPackage(){

    }
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.set*(..))")
    private void daoSetter(){

    }
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.get*(..))")
    private void daoGetter(){

    }
    @Pointcut("forDaoPackage() && !(daoGetter() || daoSetter())")
    private void forDaoPackageNoGettersNoSetters(){

    }
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(){
        System.out.println("=====>>>>> Executing @Before advice on any method with any params");

    }
    @Before("forDaoPackageNoGettersNoSetters()")
    public void apiAnalytics(){
        System.out.println("=====>>>>> Executing @Before advice  - API analytics");


    }
}
