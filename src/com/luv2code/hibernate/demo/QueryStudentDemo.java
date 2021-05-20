package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session from session factory. Session is created from session
		// factory and commit ur transaction and close it away
		Session session = factory.getCurrentSession();

		try {
			
			// Start a hibernate transaction
			session.beginTransaction();
			
			//Create a query to fetch the student object	
			List<Student> studentList = session.createQuery("from Student").getResultList();
			
			//Display student
			displayStudent(studentList);
			
			//Query with where class
			studentList = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
					
			//Display Student List
			displayStudent(studentList);
			
			
			//Query Student with lastname = 'Doe' or firstName ='Daffy'
			studentList = session.createQuery("from Student s where s.lastName='Doe'" + " OR s.firstName='Daffy'").getResultList();
			
			//Display Student List
			System.out.println("Student with FirstName = Daffy and LastName = Doe...");
			displayStudent(studentList);
			
			//Query Student with email luv2code.com
			studentList = session.createQuery("from Student s where" + " s.email LIKE '%luv2code.com'").getResultList();
			
			//Display Student List
			System.out.println("Student with email as luv2code.com...");
			displayStudent(studentList);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");

		} 
		finally {

			// finally close the factory object
			factory.close();
		}


	}

	private static void displayStudent(List<Student> studenList) {
		for(Student student: studenList) {
			System.out.println("Student : "+student);
		}
	}

}
