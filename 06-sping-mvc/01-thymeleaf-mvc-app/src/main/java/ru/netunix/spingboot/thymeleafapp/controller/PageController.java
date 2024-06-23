package ru.netunix.spingboot.thymeleafapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class PageController {
    @RequestMapping("/hello")
    public String getHelloPage(Model theModel){
        theModel.addAttribute("theDate", LocalDateTime.now());
        return "hellopage";

    }

}
