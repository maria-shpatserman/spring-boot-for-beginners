package ru.netunix.crudhibernate.dao;

import org.springframework.stereotype.Repository;
import ru.netunix.crudhibernate.entity.Instructor;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
