package ru.netunix.springcoredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.springcoredemo.beans.Coach;

@RestController
public class WorkoutController {
    //    define a private field for a dependency
    private Coach coach;

    //    define constructor injection
    @Autowired
    public WorkoutController(
            @Qualifier("aquatic") Coach injectCoach
    ) {
        System.out.println("in constructor " + this.getClass().getName());
        coach = injectCoach;
    }

    //    define any method for injection
//    @Autowired
//    public void setupMyCoach(Coach injectedCoach){
//        coach = injectedCoach;
//    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }


}
