package ru.netunix.springcoredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.springcoredemo.beans.Coach;

@RestController
public class WorkoutController {
//    define a private field for a dependency
    private Coach coach;
//    define a constructor for dependency injection
    @Autowired
    public WorkoutController(Coach coachInject){
        coach = coachInject;
    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return coach.getDailyWorkout();
    }
}
