package ru.netunix.crudhibernate.dao;

import org.springframework.stereotype.Repository;
import ru.netunix.crudhibernate.entity.Course;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
}
