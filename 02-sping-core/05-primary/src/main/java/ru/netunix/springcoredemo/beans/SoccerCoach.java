package ru.netunix.springcoredemo.beans;

import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Swim for 1 hour per day";
    }
}
