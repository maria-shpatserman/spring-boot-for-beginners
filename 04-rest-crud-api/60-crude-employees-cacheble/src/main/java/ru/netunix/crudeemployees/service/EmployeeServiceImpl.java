package ru.netunix.crudeemployees.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.netunix.crudeemployees.dao.EmployeeDAO;
import ru.netunix.crudeemployees.entity.Employee;

import java.util.List;

@Service
@CacheConfig(cacheNames = "employees")
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
    @Cacheable
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "employees",allEntries=true)
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    @CacheEvict
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
