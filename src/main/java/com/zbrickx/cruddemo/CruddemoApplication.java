package com.zbrickx.cruddemo;

import com.zbrickx.cruddemo.dao.StudentDao;
import com.zbrickx.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner ->{
			System.out.println("Welcome to our app");
			//addStudentToSystem(studentDao);
			//findStudentById(studentDao);
			//findStudentsByLastName(studentDao);
			getAllStudents(studentDao);
		};
	}

	private void addStudentToSystem(StudentDao studentDao) {
		System.out.println("Creating a new Student");
		Student student = new Student("jhon","Doe", "jhon.doe@gmail.com");
		studentDao.save(student);
		System.out.println("Student saved, student id is: " + student.getId());
	}

	private void findStudentById(StudentDao studentDao){
		System.out.println("Retriving the required student");
		Student requiredStudent = studentDao.findbyId(3);
		if(requiredStudent != null){
			System.out.println("required student details:\n" + requiredStudent);
		}else{
			System.out.println("Sorry but unable to find the student in our database");
		}

	}
	private void findStudentsByLastName(StudentDao studentDao){
		System.out.println("getting students by their last name");
		List<Student> students = studentDao.findByLastName("Doe");
		if(!students.isEmpty()){
			for(Student student: students){
				System.out.println(student + "\n");
			}
		}
		else {
			System.out.println("there is no student with this last name in the database");
		}
	}

	private void getAllStudents(StudentDao studentDao){
		System.out.println("getting all the students from the database");
		List<Student> students = studentDao.findAll();
		if(!students.isEmpty()){
			for(Student student: students){
				System.out.println(student + "\n");
			}
		}
		else {
			System.out.println("there is no student in the database right now!");
		}
	}
}
