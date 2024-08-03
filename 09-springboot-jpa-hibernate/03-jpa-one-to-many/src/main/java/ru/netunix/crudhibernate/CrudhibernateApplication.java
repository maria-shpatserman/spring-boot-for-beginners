package ru.netunix.crudhibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.crudhibernate.dao.AppDao;
import ru.netunix.crudhibernate.entity.Course;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;
import ru.netunix.crudhibernate.entity.Review;
import ru.netunix.crudhibernate.entity.Student;

import java.util.List;

@SpringBootApplication
public class CrudhibernateApplication {

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) throws Exception {

        return (arguments) -> {
//            createCourseAndReviews(appDao);
//            retrieveCourseAndReviews(appDao);
//            deleteCourseAndReviews(appDao);
//            createCourseAndStudents(appDao);
//            findCourseAndStudents(appDao);
//            findStudentAndCourses(appDao);
            addMoreCoursesForStudent(appDao);
        };

    }

    private void addMoreCoursesForStudent(AppDao appDao) {
        int id =2;
        Student student = appDao.findStudentAndCoursesByStudentId(id);
        Course course1 = new Course("Spring Boot 3");
        Course course2 = new Course("Spring Boot 2.6");
        Course course3 = new Course("Kubernetis k8s");
        student.addCourse(course1);
        student.addCourse(course2);
        student.addCourse(course3);
        appDao.update(student);
    }

    private void findStudentAndCourses(AppDao appDao) {
        int id = 2;
        Student student = appDao.findStudentAndCoursesByStudentId(id);
        System.out.println(student);
        System.out.println(student.getCourses());
    }

    private void findCourseAndStudents(AppDao appDao) {
        int id = 10;
        Course course = appDao.findCourseAndStudentsByCourseId(id);
        System.out.println(course);
        System.out.println(course.getStudents());
    }

    private void createCourseAndStudents(AppDao appDao) {
        Course course = new Course("Java 17");
        Student student1 = new Student("Dow", "Packman", "dowl@gamil.com");
        Student student2 = new Student("Bowl", "Danny", "danny@gamil.com");
        Student student3 = new Student("Susan", "Ferry", "ferry@gamil.com");
        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        appDao.save(course);
    }

    private void deleteCourseAndReviews(AppDao appDao) {
        int id = 10;
        appDao.deleteCourseById(10);
    }

    private void retrieveCourseAndReviews(AppDao appDao) {
        int id = 10;
        Course course = appDao.findCourseAndReviewsByCourseId(id);
        System.out.println(course);
        System.out.println(course.getReviews());

    }

    private void createCourseAndReviews(AppDao appDao) {
        Course course = new Course("MS Office 2010");
        course.addReview(new Review("Very old course"));
        course.addReview(new Review("Out-dated course"));
        course.addReview(new Review("Normal for elderly peeps"));
        appDao.save(course);
    }

    private void deleteCourse(AppDao appDao) {
        int id = 10;
        appDao.deleteCourseById(10);
    }

    private void updateCourse(AppDao appDao) {
        int id = 10;
        Course course = appDao.findCourseById(id);
        course.setTitle("Java EE 6");
        appDao.update(course);
    }

    private void updateExistingInstructor(AppDao appDao) {
        int id = 1;
        Instructor instructor = appDao.findInstructorById(id);
        instructor.setFirstName("Boba");
        appDao.update(instructor);

    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
        int id = 1;
        Instructor instructor = appDao.findInstructorByIdJoinFetch(id);
        System.out.println("instructor = " + instructor);
        System.out.println("instructor's courses= " + instructor.getCourses());
    }

    private void deleteInstructorDetail(AppDao appDao) {
        int id = 1;
        appDao.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDao appDao) {
        InstructorDetail instructorDetail = appDao.findInstructorDetailById(1);
        System.out.println("Instructor Detail = " + instructorDetail);
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println("Instructor  = " + instructor);

    }

    private void deleteInstructor(AppDao appDao) {
        int id = 1;
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
        Instructor instructor2 = new Instructor("Susan", "Bowl", "dowl@ya.ru");
        InstructorDetail instructorDetail2 = new InstructorDetail("http://yan.ru", "development");
        instructor2.setInstructorDetail(instructorDetail2);
        Course course = new Course("Java Spring 10");
        Course course2 = new Course("Spring Boot 3");
        instructor2.addNewCourse(course);
        instructor2.addNewCourse(course2);


        appDao.save(instructor2);

    }

    public static void main(String[] args) {
        SpringApplication.run(CrudhibernateApplication.class, args);
    }

}
