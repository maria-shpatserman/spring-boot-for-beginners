package ru.netunix.springcoredemo.beans;

import org.springframework.stereotype.Component;

@Component
public class BaseballoCach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
