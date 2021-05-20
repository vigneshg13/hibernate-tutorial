package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// Creating a session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			session.beginTransaction();
			
			System.out.println("Getting Student with ID: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
//			session.delete(myStudent);
//			
//			session.getTransaction().commit();
//			
			//Delete Student with id = 3
			session.createQuery("delete from Student where id='3'").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done !!");
			
		}
		finally {
			factory.close();
		}
	}

}
