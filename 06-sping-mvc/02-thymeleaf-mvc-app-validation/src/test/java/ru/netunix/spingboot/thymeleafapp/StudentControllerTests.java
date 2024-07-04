package ru.netunix.spingboot.thymeleafapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.netunix.spingboot.thymeleafapp.controller.StudentController;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class StudentControllerTests {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentController())
                .build();

    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void processFormTest() throws Exception {

        MvcResult result = mockMvc.perform(post("/processStudentForm")
                        .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                        .param("firstName", "Maria")
                        .param("lastName", "Shpatserman")
                        .param("country", "Italy")
                        .param("favoriteLanguage", "Java")
                        .param("favoriteSystems", "MacOs", "Linux"))
                .andExpect(view().name("student-confirmation"))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("response {}",response.getContentAsString());
    }

    @Test
    void showStudentFormTest() throws Exception {
        this.mockMvc
                .perform(get("/showStudentForm"))
                .andExpect(view().name("student-form"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
