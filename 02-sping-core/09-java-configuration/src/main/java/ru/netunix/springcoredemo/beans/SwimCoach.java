package ru.netunix.springcoredemo.beans;

public class SwimCoach implements Coach {
    public SwimCoach() {
        System.out.println("in constructor " + this.getClass().getName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up!";
    }
}
