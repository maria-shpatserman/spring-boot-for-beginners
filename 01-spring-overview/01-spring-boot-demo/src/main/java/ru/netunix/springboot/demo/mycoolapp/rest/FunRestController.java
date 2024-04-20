package ru.netunix.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${kafka.netunix.incoming.topic}")
    private String topicName;
    @GetMapping("/hv")

    public String sayHello(){
        return "Hello World!\n"+topicName;
    }
}
