package ru.netunix.thymeleafemployees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.netunix.thymeleafemployees.dao.EmployeeDAO;
import ru.netunix.thymeleafemployees.entity.Employee;
import ru.netunix.thymeleafemployees.service.EmployeeService;

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
    void testFindEmployeeById() {
        when(daoRepository.findById(anyInt())).thenReturn(new Employee("Nana", "Yang", "yang@gmail.com"));
        Employee employee = service.findById(15);
        assertEquals("Nana", employee.getFirstName());
        assertEquals("Yang", employee.getLastName());
        assertEquals("yang@gmail.com", employee.getEmail());
    }

    @Test
    void testFindAll() {
        when(daoRepository.findAll())
                .thenReturn(Stream.of(
                        new Employee("Mahsha", "Shi", "shi@gmail.com"),
                        new Employee("Siya", "Long", "long@gmail.com")).collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("XiYa", "Long", "long@gmail.com");
        when(daoRepository.save(employee))
                .thenReturn(employee);

        Employee result = service.save(employee);
        assertEquals(employee, result);
    }

    @Test
    void testDeleteEmployee() {
        service.deleteById(1);
        verify(daoRepository,times(1)).deleteById(1);

    }


}
