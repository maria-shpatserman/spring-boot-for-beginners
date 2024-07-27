package ru.netunix.crudhibernate.dao;

import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

}
