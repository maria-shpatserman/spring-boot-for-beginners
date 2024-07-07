package ru.netunix.thymeleafemployees.dao;

import ru.netunix.thymeleafemployees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    List<Employee> findAllByOrderByLastNameAsc();
    Employee findById(int id);
    Employee save(Employee employee);

    void deleteById(int id);

}
