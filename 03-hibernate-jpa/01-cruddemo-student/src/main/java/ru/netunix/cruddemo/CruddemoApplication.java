package ru.netunix.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.cruddemo.dao.StudentDAO;
import ru.netunix.cruddemo.entity.Student;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return r -> {
//			createStudent(studentDAO);
            createMultipleStudents(studentDAO);
//            createAndRetriveStudent(studentDAO);
//            getAllStudents(studentDAO);
//            getStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAll(studentDAO);

        };

    }

    private void deleteAll(StudentDAO studentDAO) {
        int deleted = studentDAO.deleteAll();
        System.out.println("Deleted : "+deleted+" students");
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int idToDelete = 2;
        Student student = studentDAO.findById(idToDelete);
        System.out.println("Student with id : "+idToDelete +" : ["+student+"] will be deleted ");
        studentDAO.delete(idToDelete);

    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrive student with id=1;
        int desiredId = 1;
        Student student = studentDAO.findById(desiredId);
        System.out.println("Student with id: " + desiredId + " student " + student);
        //change firstName to "LaoShi"
        student.setFirstName("HuanHuan");
        //update into database
        studentDAO.updateStudent(student);
        //check result from database
        Student studentNew = studentDAO.findById(desiredId);
        System.out.println("Student with id: " + desiredId + " student " + studentNew);

    }

    private void getStudentsByLastName(StudentDAO studentDAO) {
        List<Student> listStudents = studentDAO.findByLastName("Long");
        listStudents.forEach(System.out::println);
    }

    private void getAllStudents(StudentDAO studentDAO) {
        System.out.println("Get All Students...");
        List<Student> students = studentDAO.findAll();
        students.forEach(System.out::println);
//        students
//                .stream().filter(s->!s.getLastName().equals("Vong"))
//                .peek(s->System.out.println("hello "+s)).count();
//        List<Student> sortedStudents = students.stream()
//                .sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
//                .collect(Collectors.toList());
//        sortedStudents.forEach(System.out::println);


    }

    private void createAndRetriveStudent(StudentDAO studentDAO) {
        Student studentLong = new Student("MiShu", "Long", "long.mishu@email.com");
        studentDAO.save(studentLong);
        System.out.println("Student Long " + studentLong);
        Student persistStudent = studentDAO.findById(studentLong.getId());
        System.out.println("Persist student " + persistStudent);

    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        Student studentLong = new Student("HuanHuan", "Si", "si@email.com");
        Student studentShi = new Student("MiSha", "Shi", "shi.misha@email.com");
        Student studentYang = new Student("ArteMi", "Long", "long.atemi@email.com");
        studentDAO.save(studentLong);
        studentDAO.save(studentShi);
        studentDAO.save(studentYang);


    }

    private void createStudent(StudentDAO studentDAO) {
        //create student
        Student newStudent = new Student("Ben", "Yang", "yang@email.com");

        //persist student
        System.out.println("Persist student " + newStudent);
        studentDAO.save(newStudent);

        //get id of saved object
        System.out.println("Get generated id " + newStudent.getId());

    }
}
