package com.zbrickx.cruddemo.dao;

import com.zbrickx.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDaoImpl implements StudentDao{

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Student newStudent) {
        entityManager.persist(newStudent);
    }

    @Override
    public Student findbyId(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("From Student where lastName =:lastNameKey",Student.class);
        query.setParameter("lastNameKey", lastName);
        return query.getResultList();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("From Student order by lastName desc",Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student updatedStudent) {
        entityManager.merge(updatedStudent);
    }

    @Override
    @Transactional
    public int multipleEmailUpdateByQuery(String email, String byLastName) {
        int noOfRows = entityManager.createQuery(
                "update Student set email =:emailKey where lastName=:lastNameKey")
                .setParameter("emailKey",email)
                .setParameter("lastNameKey",byLastName)
                .executeUpdate();
        return noOfRows;
    }


}
