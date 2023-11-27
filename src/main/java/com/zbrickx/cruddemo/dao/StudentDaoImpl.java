package com.zbrickx.cruddemo.dao;

import com.zbrickx.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDaoImpl implements StudentDao {

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
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
        TypedQuery<Student> query = entityManager.createQuery("From Student where lastName =:lastNameKey", Student.class);
        query.setParameter("lastNameKey", lastName);
        return query.getResultList();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("From Student order by lastName desc", Student.class);
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
                .setParameter("emailKey", email)
                .setParameter("lastNameKey", byLastName)
                .executeUpdate();
        return noOfRows;
    }

    @Override
    @Transactional
    public void deleteRecord(Integer studentId) {
        Student student = findbyId(studentId);
        if (student != null) {
            entityManager.remove(student);
            System.out.println("Record deleted successfully");
        } else {
            System.out.println("Sorry, the concerned student does not exist in our database");
        }

    }


/* If the delete method is defined as we did for the update:
@Override
@Transactional
public void delete(Student theStudent) {
    em.remove(theStudent);
}
it will throw   `java.lang.IllegalArgumentException: Removing a detached instance`
this is because the object we try to delete has a different persistence context. Now question is why not the same was
happening for update/merge. Persistent Context is different even in case of update / merge operation but merge method
attach the object internally. That's why, "java.lang.IllegalArgumentException: Removing a detached instance" error doesn't
occur in update operation. One can check whether Persistent Context is different by using below code:

entityManager.contains(studentEntity)
if this returns true, it means, studentEntity object is part of the same Context, otherwise, different context.
Here, PersistentContext is based on @Transactional annotation. It's same for the @Transactional annotated method. */

/*
Another solution:
This can be fixed with the solution provided in the course or, EntityManager. merge() operation, used to merge a
detached object into the persistence context:

@Override
@Transactional
public void delete(Student theStudent) {
    theStudent = em.merge(theStudent);
    em.remove(theStudent);
}
Note: merge() does not directly update the object into the database, it merges the changes into the persistence context
(transaction)*/

    @Override
    @Transactional
    public int deleteAll() {
        int noOfRowsDeleted = entityManager.createQuery("DELETE from Student").executeUpdate();
        return noOfRowsDeleted;
    }

}
