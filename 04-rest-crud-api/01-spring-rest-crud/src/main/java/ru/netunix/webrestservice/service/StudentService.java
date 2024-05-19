package ru.netunix.webrestservice.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netunix.webrestservice.entity.Student;
import ru.netunix.webrestservice.utils.AppUtils;
import ru.netunix.webrestservice.utils.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentService {
    private List<Student> studentList;

    @PostConstruct
    void init() {
        studentList = new ArrayList<>() {
            {
                add(new Student(0,"SiYa", "Long"));
                add(new Student(1,"Nana", "Yang"));
                add(new Student(2,"MaSha", "Shi"));
            }

        };
    }


    public List<Student> getStudents() {
        log.info("[{}]: working {}", this.getClass().getName(), AppUtils.getCurrentMethodName());
        return studentList;
    }
    public Response getStudentById(int studentId){
        Optional<Student> student = studentList.stream().filter(s -> s.getId() == studentId).findFirst();
        return student.map(s->Response.builder().data(s).status(200).message("request successfull").build())
                .orElseGet(()-> Response.builder().status(422).data(null).message("student with given id not found").build());
    }
    public Optional<Student> getCustomStudentById(int id) {
        return studentList.stream().filter(s -> s.getId() == id).findFirst();
    }


}
