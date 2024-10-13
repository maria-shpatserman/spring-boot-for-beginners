package ru.netunix.thymeleafemployees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import ru.netunix.thymeleafemployees.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTests {
    //TODO fix the tests
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

    }

    @AfterEach
    public void cleanup() {
        jdbcTemplate.execute("delete  from Employee");

    }

    @Test
    @DisplayName("Positive: Get all Employees")
    @Sql(scripts = "classpath:/initialize-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGetAllEmployees() {
        List<Employee> listEmployee = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(3, listEmployee.size());

    }

    @Test
    @DisplayName("Positive: Add Employee")
    public void testAddEmployee() {
        Employee employee = new Employee("SiYa", "Long", "siya@gmail.com");
        Employee responseEmployee = restTemplate.postForObject(baseUrl, employee, Employee.class);
        assertEquals("SiYa", responseEmployee.getFirstName());
        assertEquals("Long", responseEmployee.getLastName());
        assertEquals("siya@gmail.com", responseEmployee.getEmail());
        assertEquals(1, employeeCount());

    }

    @Test
    @DisplayName("Positive: Get Employee by Id")
    @Sql(statements = "insert into Employee(id, first_name, last_name, email) values(1, 'Jay', 'Long', 'long@gmail.com');\n", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testFindEmployeeById() {
        Employee responseEmployee = restTemplate.getForObject(baseUrl + "/{id}",
                Employee.class, 1);
        assertAll(
                () -> assertNotNull(responseEmployee),
                () -> assertEquals("Jay", responseEmployee.getFirstName())
        );


    }

    @Test
    @DisplayName("Positive: Update Employee with Id")
    @Sql(statements = "insert into Employee(id, first_name, last_name, email) values(2, 'May', 'Ling', 'ling@gmail.com');\n", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdateProduct() {
        Employee employee = new Employee("May", "Ling", "ling-a@gmail.com");
        employee.setId(2);
        restTemplate.put(baseUrl, employee, Employee.class);
        Employee employeeDb = jdbcTemplate.query("SELECT * FROM EMPLOYEE WHERE id=2",
                (rs, rownum) -> new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                )
        ).stream().findFirst().get();
        assertAll(
                () -> assertNotNull(employeeDb),
                () -> assertEquals("May", employeeDb.getFirstName()),
                () -> assertEquals("ling-a@gmail.com", employeeDb.getEmail()),
                () -> assertEquals(1, employeeCount())
        );

    }

    @Test
    @DisplayName("Positive: Delete Employee by Id")
    @Sql(statements = "insert into Employee(id, first_name, last_name, email) values(3, 'May', 'Ling', 'ling@gmail.com');\n", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteEmployee() {
        assertEquals(1, employeeCount());
        restTemplate.delete(baseUrl + "/{employeeId}", 3);
        assertEquals(0, employeeCount());

    }


    public Integer employeeCount() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM EMPLOYEE", Integer.class);

    }

}
