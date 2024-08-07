package ru.netunix.thymeleafemployees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Employee> employeeList = employeeService.findAllByOrderByLastNameAsc();
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
    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int id, Model theModel){
        //get the employee using service from db
        Employee employee = employeeService.findById(id);
        //set employee into the model to pre-populate the form
        theModel.addAttribute("employee",employee);
        //send model to the form
        return  "employees/form-add-employee";
    }
    @PostMapping("/save")
    public String saveNewEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);
        //use redirect to prevent duplicate submissions

        return "redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
