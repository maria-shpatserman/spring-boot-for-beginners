package ru.netunix.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.cruddemo.dao.StudentDAO;
import ru.netunix.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return r->{
			createStudent(studentDAO);
		};
	}

    private void createStudent(StudentDAO studentDAO) {
        //create student
        Student newStudent = new Student("Ben", "Yang","yang@email.com" );

        //persist student
        System.out.println("Persist student "+ newStudent);
        studentDAO.save(newStudent);

        //get id of saved object
        System.out.println("Get generated id "+ newStudent.getId());

    }
}
