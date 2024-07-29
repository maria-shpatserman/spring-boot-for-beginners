package ru.netunix.crudhibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.crudhibernate.dao.AppDao;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

@SpringBootApplication
public class CrudhibernateApplication {

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) throws Exception {

        return (arguments) -> {
//			createInstructor(appDao );
//            findInstructor(appDao);
//            deleteInstructor(appDao);
//            findInstructorDetail(appDao);
            deleteInstructorDetail(appDao);

        };

    }



    private void deleteInstructorDetail(AppDao appDao) {
        int id = 3;
        appDao.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDao appDao) {
        InstructorDetail instructorDetail = appDao.findInstructorDetailById(3);
        System.out.println("Instructor Detail = " + instructorDetail);
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println("Instructor  = " + instructor);

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
        Instructor instructor = new Instructor("Carl", "Bravo", "bravo@ya.ru");
        InstructorDetail instructorDetail = new InstructorDetail("http://netunix.ru", "programming");
        instructor.setInstructorDetail(instructorDetail);

        appDao.save(instructor);
        Instructor instructor2 = new Instructor("Lary", "Dowl", "dowl@ya.ru");
        InstructorDetail instructorDetail2 = new InstructorDetail("http://yandex.ru", "development");
        instructor2.setInstructorDetail(instructorDetail2);


        appDao.save(instructor2);
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudhibernateApplication.class, args);
    }

}
