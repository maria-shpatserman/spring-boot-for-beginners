package ru.netunix.crudeemployees.service;

import ru.netunix.crudeemployees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
