package ru.netunix.aopspring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLogAsyncAspect {
    @Before("ru.netunix.aopspring.aspect.AopPointcuts.forDaoPackageNoGettersNoSetters()")
    public void logToCloudAsync(){
        System.out.println("=====>>>>> Logging to Cloud in async fashion");


    }
}
