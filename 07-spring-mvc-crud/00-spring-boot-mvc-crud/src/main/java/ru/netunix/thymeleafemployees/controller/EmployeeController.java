package ru.netunix.thymeleafemployees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.netunix.thymeleafemployees.entity.Employee;
import ru.netunix.thymeleafemployees.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getAllEmployees(Model theModel) {
        List<Employee> employeeList = employeeService.findAll();
        theModel.addAttribute("employeeList", employeeList);
        return "employees/list-employees";
    }

    @GetMapping("showFormForAdd")
    public String addNewEmployee(Model theModel) {
        //create model attribute to bind the form data
        Employee employee = new Employee();
        theModel.addAttribute("employee", employee);
        return  "employees/form-add-employee";
    }
}
