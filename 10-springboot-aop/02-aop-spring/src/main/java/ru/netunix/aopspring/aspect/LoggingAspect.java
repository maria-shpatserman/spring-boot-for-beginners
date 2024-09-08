package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.netunix.aopspring.entity.Account;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {

    @Before("ru.netunix.aopspring.aspect.AopPointcuts.forDaoPackageNoGettersNoSetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("=====>>>>> Executing @Before advice on any method with any params");
        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: "+methodSignature);
        //display method arguments
        Object[] args = joinPoint.getArgs();
        for (Object tempArg: args){
            System.out.println(tempArg);
            if(tempArg instanceof Account){
                Account account = (Account) tempArg;
                System.out.println("account name "+account.getName());
                System.out.println("account amount "+account.getAmount());
            }
        }


    }


}
