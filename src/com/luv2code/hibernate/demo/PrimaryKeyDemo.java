package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session from session factory. Session is created from session
		// factory and commit ur transaction and close it away
		Session session = factory.getCurrentSession();

		try {
			// create 3 student object via constructor
			System.out.println("Creating 3 Student Objects...");
			Student tempStudent1 = new Student("Bonita", "Applebum", "bonita@luv2code.com");
			Student tempStudent2 = new Student("John", "Doe", "john.doe@luv2code.com");
			Student tempStudent3 = new Student("Mary", "public", "mary.public@luv2code.com");
			

			// Start a hibernate transaction
			session.beginTransaction();

			// Save the transaction
			System.out.println("Saving Students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			// commit the transaction for the session
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {

			// finally close the factory object
			factory.close();
		}


	}

}
