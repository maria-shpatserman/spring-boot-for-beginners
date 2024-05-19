package ru.netunix.crudeemployees.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netunix.crudeemployees.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
