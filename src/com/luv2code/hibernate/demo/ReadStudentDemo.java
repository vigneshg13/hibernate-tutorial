package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session from session factory. Session is created from session
		// factory and commit ur transaction and close it away
		Session session = factory.getCurrentSession();

		try {
			// create a new student object via constructor
			System.out.println("Creating Student Object...");
			
			Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

			// Start a hibernate transaction
			session.beginTransaction();

			// Save the transaction
			System.out.println("Saving the Studen to DB...");
			
			session.save(tempStudent);
			
			System.out.println("Student Saved: " +tempStudent);

			// commit the transaction for the session
			session.getTransaction().commit();
			
			System.out.println("Saved Student Object: "+ tempStudent.getId());
			
			// Now get a session
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			//reterive the student based on the id: primarykey
			Student student = session.get(Student.class,tempStudent.getId());
			
			System.out.println("Computed Student: "+ student);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");

		} 
		finally {

			// finally close the factory object
			factory.close();
		}


	}

}
