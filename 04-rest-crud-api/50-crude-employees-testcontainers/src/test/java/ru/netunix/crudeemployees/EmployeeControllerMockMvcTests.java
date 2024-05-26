package ru.netunix.crudeemployees;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.netunix.crudeemployees.entity.Employee;
import ru.netunix.crudeemployees.rest.EmployeeRestController;
import ru.netunix.crudeemployees.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerMockMvcTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeRestController(employeeService))
                .build();

    }
    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    @DisplayName("Positive: Get all Employees - MockMvc")
//    @Sql(scripts = "classpath:/initialize-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/employees")
                        .contentType("application/json")
                        .accept("application/json")
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists()
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value()
                        );


    }

    @Test
    @DisplayName("Positive: Add new Employees - MockMvc")
    public void testAddNewEmployees() throws Exception {
        Employee employee = new Employee("SiYa", "Long", "siya@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/employees")
                        .contentType("application/json")
                        .content(asJsonString(employee))
                        .accept("application/json")
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    public String asJsonString(Object item) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(item);
    }

}
