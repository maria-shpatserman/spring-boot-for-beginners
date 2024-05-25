package ru.netunix.crudeemployees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.netunix.crudeemployees.dao.EmployeeDAO;
import ru.netunix.crudeemployees.entity.Employee;
import ru.netunix.crudeemployees.service.EmployeeService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class EmployeeServiceTests {
    @Autowired
    private EmployeeService service;
    @MockBean
    private EmployeeDAO daoRepository;

    @Test
    void findEmployeeByIdTest() {
        when(daoRepository.findById(anyInt())).thenReturn(new Employee("Nana", "Yang", "yang@gmail.com"));
        Employee employee = service.findById(15);
        assertEquals("Nana", employee.getFirstName());
        assertEquals("Yang", employee.getLastName());
        assertEquals("yang@gmail.com", employee.getEmail());
    }

    @Test
    void findAllTest() {
        when(daoRepository.findAll())
                .thenReturn(Stream.of(
                        new Employee("Mahsha", "Shi", "shi@gmail.com"),
                        new Employee("Siya", "Long", "long@gmail.com")).collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    void saveEmployeeTest() {
        Employee employee = new Employee("XiYa", "Long", "long@gmail.com");
        when(daoRepository.save(employee))
                .thenReturn(employee);

        Employee result = service.save(employee);
        assertEquals(employee, result);
    }

    @Test
    void deleteEmployeeTest() {
        service.deleteById(1);
        verify(daoRepository,times(1)).deleteById(1);

    }


}
