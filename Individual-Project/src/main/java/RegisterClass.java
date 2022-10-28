

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Course;
import datamodel.Student;
import util.UtilCourse;
import util.UtilStudent;

/**
 * Servlet implementation class RegisterClass
 */
@WebServlet("/RegisterClass")
public class RegisterClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer studentId = Integer.parseInt(request.getParameter("studentId"));
		String courseId= request.getParameter("courseId");
		System.out.println("Course id is " + courseId);
		
		Student student = UtilStudent.findStudentId(studentId);
		
		Course course= UtilCourse.findCourseId(courseId);
		UtilCourse.addStudentToCourse(course, studentId);
		UtilStudent.addClassToStudent(student, courseId);
		
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Added class to Student list";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "Course: " + course.getCourseName() + " has been added to " + student.getFirstName()+ " "+ student.getLastName() +
	            "\n");

	      out.println("<a href=\"StudentSearch.html\">Search Student</a> <br>");
	      out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
