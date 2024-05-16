package ru.netunix.crudeemployees.dao;

import ru.netunix.crudeemployees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);

    void deleteById(int id);

}
