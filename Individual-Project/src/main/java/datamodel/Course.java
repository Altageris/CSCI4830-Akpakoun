package datamodel;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "MyCourseTableAkpakoun")
public class Course {
	@Id
	@Column (name = "courseId")
	private String courseId;
	@Column (name = "COURSE_NAME")
	private String courseName;
	@Column (name = "DESCRIPTION")
	private String description;
	@Column (name = "ENROLLED_STUDENTS")
	private String enrolledStudents="";
	
	public Course(String courseId, String courseName, String description ) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.description= description;
		this.enrolledStudents= "";
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnrolledStudents() {
		return enrolledStudents;
	}
	public Course() {
		super();
	}
	public void setEnrolledStudents(String enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public boolean addStudent(Integer studentId) {
		if(this.enrolledStudents=="") {
			this.enrolledStudents = ""+studentId;
		}
		else {
		 this.enrolledStudents += ","+studentId;
		}
		 return true;
	}
	
}
