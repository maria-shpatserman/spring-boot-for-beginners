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
    private Coach anotherCoach;
//    define constructor injection
    @Autowired
  public WorkoutController(@Qualifier("baseballCoach") Coach injectCoach,
                           @Qualifier("baseballCoach") Coach anotherInjectCoach){
        System.out.println("in constructor "+this.getClass().getName());
        coach = injectCoach;
        anotherCoach = anotherInjectCoach;
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
    @GetMapping("/check")
    public String check(){
        return "Compare coach == anotherCoach result : "+(coach == anotherCoach);
    }
}
