package com.zbrickx.cruddemo.dao;

import com.zbrickx.cruddemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student newStudent);
    Student findbyId(Integer id);
    List<Student> findByLastName(String lastName);
    List<Student> findAll();

}
