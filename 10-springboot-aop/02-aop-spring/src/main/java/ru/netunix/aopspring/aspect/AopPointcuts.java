package ru.netunix.aopspring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcuts {
    //apply to all method in all classes in DAO package
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.*(..))")
    public void forDaoPackage(){

    }
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.set*(..))")
    public void daoSetter(){

    }
    @Pointcut("execution(* ru.netunix.aopspring.dao.*.get*(..))")
    public void daoGetter(){

    }
    @Pointcut("forDaoPackage() && !(daoGetter() || daoSetter())")
    public void forDaoPackageNoGettersNoSetters(){

    }
}
