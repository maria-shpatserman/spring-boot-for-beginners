package ru.netunix.springcoredemo.beans;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Running for 20 minutes every day";
    }
}
