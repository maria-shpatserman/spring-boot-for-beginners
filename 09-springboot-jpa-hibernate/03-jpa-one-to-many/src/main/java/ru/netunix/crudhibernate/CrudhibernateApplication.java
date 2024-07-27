package ru.netunix.crudhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.crudhibernate.dao.AppDao;
import ru.netunix.crudhibernate.entity.Course;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

import java.sql.SQLOutput;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
public class CrudhibernateApplication {

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) throws Exception {

        return (arguments) -> {
//			createInstructor(appDao );
//            findInstructor(appDao);
//            deleteInstructor(appDao);
//            findInstructorDetail(appDao);
//            deleteInstructorDetail(appDao);
//                       createInstructorWithCourses(appDao);
//            findInstructorWithCourses(appDao);
//            findCoursesForInstructor(appDao);
            findInstructorWithCoursesJoinFetch(appDao);

        };

    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
        int id =1;
        Instructor instructor = appDao.findInstructorByIdJoinFetch(id);
        System.out.println("instructor = "+instructor);
        System.out.println("instructor's courses= "+instructor.getCourses());
    }

    private void deleteInstructorDetail(AppDao appDao) {
        int id =1;
        appDao.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDao appDao) {
        InstructorDetail instructorDetail = appDao.findInstructorDetailById(1);
        System.out.println("Instructor Detail = "+instructorDetail);
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println("Instructor  = "+instructor);

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
		Instructor instructor = new Instructor("Carl","Bravo","bravo@ya.ru");
		InstructorDetail instructorDetail = new InstructorDetail("http://netunix.ru","programming");
		instructor.setInstructorDetail(instructorDetail);

        appDao.save(instructor);
        Instructor instructor2 = new Instructor("Lary", "Dowl", "dowl@ya.ru");
        InstructorDetail instructorDetail2 = new InstructorDetail("http://yandex.ru", "development");
        instructor2.setInstructorDetail(instructorDetail2);


        appDao.save(instructor2);
    }
    private void findCoursesForInstructor(AppDao appDao) {
        int id = 2;
        Instructor instructor = appDao.findInstructorById(id);
        System.out.println("instructor " + instructor);
        //find courses
        List<Course> courses = appDao.findCoursesByInstructorId(id);
        instructor.setCourses(courses);
        System.out.println("the associated course: " + instructor.getCourses());
    }

    private void findInstructorWithCourses(AppDao appDao) {
        int id = 2; // for EAGER
        Instructor instructor = appDao.findInstructorById(id);
        System.out.println("instructor " + instructor);
        System.out.println("the associated course: " + instructor.getCourses());
    }

    private void createInstructorWithCourses(AppDao appDao) {
        Instructor instructor2 = new Instructor("Susan", "Dowl", "dowl@ya.ru");
        InstructorDetail instructorDetail2 = new InstructorDetail("http://yan.ru", "development");
        instructor2.setInstructorDetail(instructorDetail2);
        Course course = new Course("Java Spring");
        Course course2 = new Course("Spring Boot");
        instructor2.addNewCourse(course);
        instructor2.addNewCourse(course2);


        appDao.save(instructor2);

    }

    public static void main(String[] args) {
        SpringApplication.run(CrudhibernateApplication.class, args);
    }

}
