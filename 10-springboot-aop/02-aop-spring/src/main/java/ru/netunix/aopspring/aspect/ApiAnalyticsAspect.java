package ru.netunix.aopspring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class ApiAnalyticsAspect {
    @Before("ru.netunix.aopspring.aspect.AopPointcuts.forDaoPackageNoGettersNoSetters()")
    public void apiAnalytics(){
        System.out.println("=====>>>>> Performing - API analytics");


    }

}
