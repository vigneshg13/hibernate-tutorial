package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session from session factory. Session is created from session
		// factory and commit ur transaction and close it away
		Session session = factory.getCurrentSession();

		try {
			
			int studentId=1;
			// Start a hibernate transaction
			session.beginTransaction();
			
			//reterive student based on the id: primary key
			System.out.println("\nGetting student with ID: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			
			System.out.println("Updating Student...");
			
			//Updating student FirstName
			myStudent.setFirstName("scooby");
			myStudent.setEmail("paul.wilson@gmail.com");
			
			//commit the transaction
			session.getTransaction().commit();
		
			//New Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("Update Email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!!");

		} 
		finally {

			// finally close the factory object
			factory.close();
		}


	}

}
