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
			//getAllStudents(studentDao);
			//updateStudent(studentDao);
			updateStudentEmailByLastName(studentDao);
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

	private void updateStudent(StudentDao studentDao){
		System.out.println("updating a student with id 2");
		Student requiredStudent = studentDao.findbyId(2);
		Student newStd =  new Student("Daffy","Duck","daffy.duck@gmail.com");
		//requiredStudent = studentDao.findbyId(2);
		requiredStudent = newStd;
		if(requiredStudent != null) {
			requiredStudent.setFirstName("Scobby");
			studentDao.update(requiredStudent);
			System.out.println("record update successfully");
		}else{
			System.out.println("unable to find the required student");
		}
	}

	private void updateStudentEmailByLastName(StudentDao studentDao){
		System.out.println("updating all students email whose last name is doe");
		int noOfRows = studentDao.multipleEmailUpdateByQuery("scobbydobby@do.com","doe");
		if(noOfRows > 0)
			System.out.println("Update Successfully, " + noOfRows + " rows affected");
		else
			System.out.println("did not find any record that matches the required creteria");
	}
}
