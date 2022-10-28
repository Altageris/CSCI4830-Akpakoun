/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Course;
import datamodel.Student;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilCourse {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Course> listCourses() {
      List<Course> resultList = new ArrayList<Course>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> courses = session.createQuery("FROM MyCourseTableAkpakoun").list();
         for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
            Course course = (Course) iterator.next();
            resultList.add(course);
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
   public static Course findCourseId(String courseId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Course course = null;
		 try{
		tx = session.beginTransaction();
		System.out.println(courseId);
		Query query = session.createQuery("FROM Course where courseId = \'" + courseId +"\'");
//		query.setString("courseId", courseId);
		
		 course = (Course) query.list().get(0);
		 System.out.println("Class found: " + course.getCourseName());
		 
		tx.commit();
		return course;
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 return course;
		 
		
		
	}
   public static void addStudentToCourse(Course course, Integer studentId) {
	   course.addStudent(studentId);
	   Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(course); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	
	   
   }
   public static void createCourse(Course course) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(course);
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
