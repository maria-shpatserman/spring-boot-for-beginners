package ru.netunix.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.netunix.cruddemo.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
    //    define field for entity manager
    private EntityManager entityManager;

    //    inject entity manager at constructor
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
