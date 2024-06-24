package ru.netunix.spingboot.thymeleafapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PageController {
    @RequestMapping("/hello")
    public String getHelloPage(Model theModel) {
        theModel.addAttribute("theDate", LocalDateTime.now().toLocalDate());
        return "hellopage";

    }

    @RequestMapping("/showForm")
    public String showInitForm(Model theModel) {
        return "show-form";

    }

    @RequestMapping("/processForm")
    public String processInitForm(Model theModel, @RequestParam("studentName") String studentName){
        theModel.addAttribute("theStudentName", studentName);
        return "greeting";
    }
}
