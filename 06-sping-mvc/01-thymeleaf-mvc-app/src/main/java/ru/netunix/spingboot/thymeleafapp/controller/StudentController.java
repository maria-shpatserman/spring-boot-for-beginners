package ru.netunix.spingboot.thymeleafapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.netunix.spingboot.thymeleafapp.model.Student;

@Controller
@Slf4j
public class StudentController {
    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);

        return "student-form";

    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent) {

        log.info("{}", theStudent);

        return "student-confirmation";
    }
}
