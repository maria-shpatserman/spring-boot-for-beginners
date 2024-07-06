package ru.netunix.thymeleafemployees.service;

import ru.netunix.thymeleafemployees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);

    void deleteById(int id);
}
