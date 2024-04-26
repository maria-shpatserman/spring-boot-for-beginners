package ru.netunix.springcoredemo.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    public BaseballCoach() {
        System.out.println("in constructor " + this.getClass().getName());
    }
    // define init method
    @PostConstruct
    public void doInit(){
        System.out.println("in post construction "+this.getClass().getName());
    }
    // define destroy method
    @PreDestroy
    public void doCleanup(){
        System.out.println("in pre destroy - do cleanup "+this.getClass().getName());
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
