package ru.netunix.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.netunix.aopspring.entity.Account;

import java.util.List;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {
    @Around("execution(* ru.netunix.aopspring.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //print out method we are advising for
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("=====>>>>> Executing @Around () advice after " + methodName);

        //get begin timestamp
        long begin = System.currentTimeMillis();
        //execute the method
        Object result = proceedingJoinPoint.proceed();
        System.out.println("=====>>>>> Executing @Around () advice proceed result " + result.toString());
        //get end timestamp
        long end = System.currentTimeMillis();
        //compute duration and display it
        long duration = end - begin;
        System.out.println("=====>>>>> Duration " +duration/1000.0 +" seconds");

        return result;
    }

    @After("execution(* ru.netunix.aopspring.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
        String methodName = theJoinPoint.getSignature().toShortString();
        System.out.println("=====>>>>> Executing @After (finally) advice after " + methodName);


    }

    @AfterReturning(
            pointcut = "execution(* ru.netunix.aopspring.dao.AccountDao.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("=====>>>>> Executing @AfterReturning advice after " + methodName);
        System.out.println("=====>>>>> result is " + result);
        //let's post-process data
        convertNamesToUpperCase(result);

    }

    @AfterThrowing(
            pointcut = "execution(* ru.netunix.aopspring.dao.AccountDao.findAccounts(..))",
            throwing = "theException"
    )
    public void afterThrowingExceptionFindAccountsAdvice(JoinPoint joinPoint, Throwable theException) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("=====>>>>> Executing @AfterThrowing advice after " + methodName);
        System.out.println("=====>>>>> theException is " + theException.toString());
    }

    private void convertNamesToUpperCase(List<Account> result) {
        result.stream().forEach(s -> s.setName(s.getName().toUpperCase()));
    }

    @Before("ru.netunix.aopspring.aspect.AopPointcuts.forDaoPackageNoGettersNoSetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("=====>>>>> Executing @Before advice on any method with any params");
        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);
        //display method arguments
        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account) {
                Account account = (Account) tempArg;
                System.out.println("account name " + account.getName());
                System.out.println("account amount " + account.getAmount());
            }
        }


    }


}
