package ru.netunix.crudhibernate.dao;

import org.springframework.stereotype.Repository;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.Course;
import ru.netunix.crudhibernate.entity.InstructorDetail;
import ru.netunix.crudhibernate.entity.Student;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    void update(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);
    void save (Course course);
    Course findCourseAndReviewsByCourseId(int id);
    Course findCourseAndStudentsByCourseId(int id);
    Student findStudentAndCoursesByStudentId(int id);
    void update(Student student);
}
