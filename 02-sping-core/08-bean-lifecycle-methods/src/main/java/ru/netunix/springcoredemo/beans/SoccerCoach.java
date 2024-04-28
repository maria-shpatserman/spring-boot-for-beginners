package ru.netunix.springcoredemo.beans;

import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach {
    public SoccerCoach() {
        System.out.println("in constructor " + this.getClass().getName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim for 1 hour per day";
    }
}
