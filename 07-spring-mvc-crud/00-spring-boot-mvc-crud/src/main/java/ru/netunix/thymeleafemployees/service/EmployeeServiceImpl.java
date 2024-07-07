package ru.netunix.thymeleafemployees.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netunix.thymeleafemployees.dao.EmployeeDAO;
import ru.netunix.thymeleafemployees.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = employeeDAO.findAll();
        System.out.println("I am here "+employeeList);
        return employeeList;
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public List<Employee> findAllByOrderByLastNameAsc() {
        return employeeDAO.findAllByOrderByLastNameAsc();
    }
}
