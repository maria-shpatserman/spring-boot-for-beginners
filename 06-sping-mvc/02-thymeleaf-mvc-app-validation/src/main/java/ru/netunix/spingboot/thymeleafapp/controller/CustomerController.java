package ru.netunix.spingboot.thymeleafapp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.netunix.spingboot.thymeleafapp.model.Customer;

@Controller
@Slf4j
public class CustomerController {
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showCustomerForm(Model theModel) {
        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/processCustomerForm")
    public String processCustomerForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {
        log.info("Last name: [{}]", theCustomer.getLastName());
        if (theBindingResult.hasErrors()) {
            return "customer-form";
        }
        return "customer-confirmation";
    }
}
