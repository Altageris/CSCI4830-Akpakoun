

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Course;
import util.UtilCourse;

/**
 * Servlet implementation class InsertClass
 */
@WebServlet("/InsertCourse")
public class InsertCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String courseId= request.getParameter("courseId");
		String courseName = request.getParameter("name");
	      String description = request.getParameter("description");
	      Course newCourse = new Course(courseId, courseName, description);
	     UtilCourse.createCourse(newCourse);
	      
	      
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Insert Data to Course table";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>Course name</b>: " + courseName + "\n" + //
	            "  <li><b>Description</b>: " + description + "\n" + //

	            "</ul>\n");

	      out.println("<a href=/webproject/SearchClass.html>Search class</a> <br>");
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
