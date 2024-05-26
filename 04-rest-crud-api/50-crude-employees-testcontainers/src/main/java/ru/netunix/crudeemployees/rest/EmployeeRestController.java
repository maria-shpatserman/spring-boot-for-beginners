package ru.netunix.crudeemployees.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.crudeemployees.entity.Employee;
import ru.netunix.crudeemployees.service.EmployeeService;
import ru.netunix.crudeemployees.utils.EmployeeNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if( employee == null) {
            throw  new EmployeeNotFoundException(String.format("Employee with id [%d] not found",employeeId));
        }
        return employee;
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if( employee == null) {
            throw  new EmployeeNotFoundException(String.format("Employee with id [%d] not found",employeeId));
        }
        employeeService.deleteById(employeeId);
        return String.format("%s was deleted",employee.toString());
    }
}
