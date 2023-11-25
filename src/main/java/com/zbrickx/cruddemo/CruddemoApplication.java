package com.zbrickx.cruddemo;

import com.zbrickx.cruddemo.dao.StudentDao;
import com.zbrickx.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner ->{
			System.out.println("Welcome to our app");
			addStudentToSystem(studentDao);
		};
	}

	private void addStudentToSystem(StudentDao studentDao) {
		System.out.println("Creating a new Student");
		Student student = new Student("jhon","Doe", "jhon.doe@gmail.com");
		studentDao.save(student);
		System.out.println("Student saved, student id is: " + student.getId());
	}
}
