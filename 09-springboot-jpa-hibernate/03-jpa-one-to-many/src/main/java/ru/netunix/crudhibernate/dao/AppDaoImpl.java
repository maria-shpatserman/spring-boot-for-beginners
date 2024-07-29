package ru.netunix.crudhibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netunix.crudhibernate.entity.Course;
import ru.netunix.crudhibernate.entity.Instructor;
import ru.netunix.crudhibernate.entity.InstructorDetail;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {
    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);

    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> list = findCoursesByInstructorId(id);
        list.forEach(c->c.setInstructor(null));

        entityManager.remove(instructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, id);
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                " select i from Instructor i "
                        + " JOIN FETCH i.courses "
                        + " JOIN FETCH i.instructorDetail "
                        + " where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        Instructor instructor = query.getSingleResult();
        return instructor;

    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);

    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                " select i from Course i "
                        + " JOIN FETCH i.reviews "
                        + " where i.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }
}
