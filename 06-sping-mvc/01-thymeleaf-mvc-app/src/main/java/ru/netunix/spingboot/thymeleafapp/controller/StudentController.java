package ru.netunix.spingboot.thymeleafapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.netunix.spingboot.thymeleafapp.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@Slf4j
public class StudentController {
    @Value("${countries}")
    List<String> countries;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        theModel.addAttribute("countries",countries);

        return "student-form";

    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent) {

        log.info("{}", theStudent);
        String input = "Test   reversed words   frame";

        log.info("input {}", input);
        log.info("result {}", reverseWords(input));
        return "student-confirmation";
    }

    String reverseWords(String input) {
        Set<String> keys = Set.copyOf(Arrays.asList(input.split(" ")));

        String result = input;
        for (String key : keys) {
            StringBuilder reversed = new StringBuilder(key).reverse();
            result = result.replace(key, reversed.toString());
        }
        return result;
    }
}
