

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Student;
import util.UtilStudent;

/**
 * Servlet implementation class AllStudents
 */
@WebServlet("/AllStudents")
public class AllStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  List <Student> students =  UtilStudent.listStudents();
	      
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Insert Data to Student table";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n");
	      for(int i=0; i< students.size(); i++ ) {
	    	  out.print("<li> StudentID: "+ students.get(i).getStudentId() +" 	 "+ students.get(i).getFirstName() + " 	" + students.get(i).getLastName()+ " 	 " + students.get(i).getEmail() 	
	    	  		+ " </li>");
	      }


	          out.print("</ul>\n");

	      out.println("<a href=/Individual-Project/StudentSearch.html>Search Student</a> <br>");
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
