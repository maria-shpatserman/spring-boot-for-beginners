package ru.netunix.crudhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.crudhibernate.dao.AppDao;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
public class CrudhibernateApplication {

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) throws Exception {

        return (arguments) -> {
//			createInstructor(appDao );
//            findInstructor(appDao);
            deleteInstructor(appDao);
        };

    }

    private void deleteInstructor(AppDao appDao) {
        int id = 2;
        System.out.println("Deleting instructor wit id = " + id);
        appDao.deleteInstructorById(id);
    }

    private void findInstructor(AppDao appDao) {
        int id = 1;
        System.out.println("Find the instructor with id  = " + id);
        Instructor instructorById = appDao.findInstructorById(id);
        System.out.println(instructorById.toString());
    }

    private void createInstructor(AppDao appDao) {
//		Instructor instructor = new Instructor("Carl","Bravo","bravo@ya.ru");
//		InstructorDetail instructorDetail = new InstructorDetail("http://netunix.ru","programming");
//		instructor.setInstructorDetail(instructorDetail);

        Instructor instructor = new Instructor("Lary", "Dowl", "dowl@ya.ru");
        InstructorDetail instructorDetail = new InstructorDetail("http://yandex.ru", "development");
        instructor.setInstructorDetail(instructorDetail);


        appDao.save(instructor);
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudhibernateApplication.class, args);
    }

}
