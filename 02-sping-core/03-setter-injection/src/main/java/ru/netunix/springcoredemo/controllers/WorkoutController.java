package ru.netunix.springcoredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.springcoredemo.beans.Coach;

@RestController
public class WorkoutController {
//    define a private field for a dependency
    private Coach coach;
//    define setter method for injection
    @Autowired
    public void setCoach(Coach injectedCoach){
        coach = injectedCoach;
    }

//    define any method for injection
//    @Autowired
//    public void setupMyCoach(Coach injectedCoach){
//        coach = injectedCoach;
//    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return coach.getDailyWorkout();
    }
}
