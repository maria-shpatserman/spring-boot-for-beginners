package ru.netunix.spingboot.thymeleafapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/showForm")
    public String showInitForm(Model theModel) {
        return "show-form";

    }

    @RequestMapping("/processForm")
    public String processInitForm(Model theModel, @RequestParam("studentName") String studentName) {
        theModel.addAttribute("theStudentName", studentName);
        return "greeting";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model theModel) {
        String studentName = request.getParameter("studentName").toUpperCase();
        theModel.addAttribute("theStudentName", studentName);
        theModel.addAttribute("message", "Yo! " + studentName);
        return "greeting";
    }
    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model theModel) {
        String studentName = theName.toUpperCase();
        theModel.addAttribute("theStudentName", studentName);
        theModel.addAttribute("message", "Hey my friend  " + studentName);
        return "greeting";
    }
}
