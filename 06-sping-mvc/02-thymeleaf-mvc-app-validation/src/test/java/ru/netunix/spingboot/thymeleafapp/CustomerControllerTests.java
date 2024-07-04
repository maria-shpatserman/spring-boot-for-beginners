package ru.netunix.spingboot.thymeleafapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import ru.netunix.spingboot.thymeleafapp.controller.CustomerController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class CustomerControllerTests {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController())
                .build();

    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void showCustomerFormTest() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("customer-form"))
                .andDo(print())
                .andExpect(status().isOk());


    }
    @Test
    void processFormTest() throws Exception {
//private String firstName;
//    @NotNull(message = "is required")
//    @Size(min = 1, message = "is required")
//    private String lastName;
//    @NotNull(message = "is required")
//    @Min(value = 0, message = "must be greater or equal to zero")
//    @Max(value = 10, message = "must be less or equal to 10")
//    private Integer freePasses;
//    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
//    private String postalCode;
//    @PrefixCourseCode(value = "Java", message = "must start with Java")
//    private String courseCode;
        MvcResult result = mockMvc.perform(post("/processCustomerForm")
                        .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                        .param("firstName", "Maria")
                        .param("lastName", "Shpatserman")
                        .param("freePasses", "5")
                        .param("postalCode", "J5623")
                        .param("courseCode", "Java 21"))
                .andExpect(view().name("customer-confirmation"))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("response {}",response.getContentAsString());
    }
    @Test
    @DisplayName("Negative: Check LastName is mandatory")
    void processFormNullLastNameTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/processCustomerForm")
                        .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                        .param("firstName", "Maria")
                        .param("freePasses", "5")
                        .param("postalCode", "J5623")
                        .param("courseCode", "Java 21"))
                .andExpect(view().name("customer-form"))
                .andReturn();
        BindingResult bindingResult= (BindingResult) result.getModelAndView().getModel().get("org.springframework.validation.BindingResult.customer");

        assertThat(bindingResult.getFieldError("lastName")).isNotNull();
        assertThat(bindingResult.getFieldError("lastName").getDefaultMessage()).isEqualTo("is required");
        log.info("fieldError {}",bindingResult.getFieldError().toString());
    }
}
