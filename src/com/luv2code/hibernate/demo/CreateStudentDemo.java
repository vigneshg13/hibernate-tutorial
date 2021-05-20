package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		// Create a SessionFactory which is heavy weight object just needs to be created
		// at once and shared across the application.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session from session factory. Session is created from session
		// factory and commit ur transaction and close it away
		Session session = factory.getCurrentSession();

		try {
			// create a new student object via constructor
			Student tempStudent = new Student("Paul", "Wilson", "paul.wilson@luv2code.com");

			// Start a hibernate transaction
			session.beginTransaction();

			// Save the transaction
			session.save(tempStudent);

			// commit the transaction for the session
			session.getTransaction().commit();

		} finally {

			// finally close the factory object
			factory.close();
		}

	}

}
