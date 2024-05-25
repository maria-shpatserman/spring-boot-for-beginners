package ru.netunix.crudeemployees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import ru.netunix.crudeemployees.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTests {
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUpUrl() {
        baseUrl = baseUrl.concat(":").concat(String.valueOf(port)).concat("/api/employees");
        log.info("Initializing DB..");
//        jdbcTemplate.execute("insert into Employee(first_name, last_name, email) values('Jay', 'Long', 'long@gmail.com')");

    }

    @Test
    @DisplayName("Positive: Get all Employees")
    void getAllEmployeesTest() {

    }

    @Test
    @DisplayName("Positive: Add Employee")
    public void addEmployeeTest() {
        Employee employee = new Employee("SiYa", "Long", "siya@gmail.com");
        Employee responseEmployee = restTemplate.postForObject(baseUrl, employee, Employee.class);
        assertEquals("SiYa", responseEmployee.getFirstName());
        assertEquals("Long", responseEmployee.getLastName());
        assertEquals("siya@gmail.com", responseEmployee.getEmail());
        assertEquals(1, employeeCount());

    }
    public Integer employeeCount(){
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM EMPLOYEE", Integer.class);

    }
}
