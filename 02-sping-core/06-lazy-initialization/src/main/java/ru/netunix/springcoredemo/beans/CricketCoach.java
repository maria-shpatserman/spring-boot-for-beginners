package ru.netunix.springcoredemo.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CricketCoach implements Coach{
    public CricketCoach() {
        System.out.println("in constructor "+this.getClass().getName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes every day";
    }
}
