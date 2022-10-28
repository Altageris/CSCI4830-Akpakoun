package util;

import datamodel.Course;
import datamodel.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UtilStudent {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static List<Student> listStudents() {
		List<Student> resultList = new ArrayList<Student>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Student").list();
			for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
				Student student = (Student) iterator.next();
				resultList.add(student);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	public static Student findStudentId(Integer studentId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Student student = null;
		 try{
		tx = session.beginTransaction();
		Query query = session.createQuery("FROM Student where studentId = " + studentId);
//		query.setLong("studentId", studentId);
		
		 student = (Student) query.list().get(0);
		 System.out.println("Student found: " + student.getFirstName());
		 
		tx.commit();
		return student;
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 return student;
		 
		
		
	}
	public static void addClassToStudent(Student student, String courseID) {
		student.addClass(courseID);
		Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}
	public static String listStudentClasses( Student student){
		String title = "Student classes";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      String out = docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n";
	      
		
		String classesIds =  student.getClasses(); 
		System.out.println(classesIds);
		String [] classes = classesIds.split(",");
		ArrayList <Course> courses= new ArrayList <Course>();
		
		if(classesIds.isEmpty()) {
			System.out.println("Class id is empty");
			out +=" <div>The student is currently not enrolled in any classes</div>"
					+ "<a href=\"StudentSearch.html\">Student Search</a>\n</body></html>";
			return out;
		}
		
		
		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			for(int i=0; i< classes.length; i++) {
				if(classes[i].length() <5) {
					continue;
				}
				tx = session.beginTransaction();
				Query query = session.createQuery("FROM Course where courseId = \'" + classes[i] + "\'");
				
				
				
				List <?> course = query.list();
				courses.add((Course) course.get(0));
				tx.commit();
			}
			for(int i=0; i< courses.size(); i++) {
				out+= "<div> Class: " + courses.get(i).getCourseName() + "<br> Description: "+courses.get(i).getDescription()
						+"\n </div>";
				
			}
			out+= "<a href=\"StudentSearch.html\">Student Search</a>"
					+ "</body></html>";
			return out;
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return out;
	      
	}

	public static void createStudent(Student student) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
