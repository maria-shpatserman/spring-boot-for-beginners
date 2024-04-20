package ru.netunix.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //    Inject properties
    @Value("${kafka.netunix.incoming.topic}")
    private String topicName;
    @Value("${env.name}")
    private String envName;
    @Value("${env.priority}")
    private Integer priority;

    @GetMapping("/env")
    private String getEnvInfo() {
        return new StringBuilder().append("Env name: ")
                .append(envName).append("\n").append("env priority: ").append(priority).toString();
    }

    @GetMapping("/hv")
    public String sayHello() {
        return "Hello World!\n" + topicName;
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 10k!";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "This is your lucky day!";
    }
}
