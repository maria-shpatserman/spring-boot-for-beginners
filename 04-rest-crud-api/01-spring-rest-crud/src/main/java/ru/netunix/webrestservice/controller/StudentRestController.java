package ru.netunix.webrestservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.webrestservice.entity.Student;
import ru.netunix.webrestservice.service.StudentService;
import ru.netunix.webrestservice.utils.AppUtils;
import ru.netunix.webrestservice.utils.Response;
import ru.netunix.webrestservice.utils.StudentErrorResponse;
import ru.netunix.webrestservice.utils.StudentNotFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public final class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        log.info("[{}]: working {}", this.getClass().getName(), AppUtils.getCurrentMethodName());

        return studentService.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Response getStudentId(@PathVariable int studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/students/custom/{id}")
    public Student getStudentById(@PathVariable int id) {
       return studentService.getCustomStudentById(id)
               .orElseThrow(()->new StudentNotFoundException(String.format("Student with id %d not found",id)));
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse>  handleException(StudentNotFoundException exception){
      ;
      return new ResponseEntity<>(StudentErrorResponse.builder()
              .message(exception.getMessage())
              .status(HttpStatus.NOT_FOUND.value())
              .timeStamp(System.currentTimeMillis()).build(),HttpStatus.NOT_FOUND);

    }
}
