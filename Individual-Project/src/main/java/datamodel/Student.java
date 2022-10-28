package datamodel;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MyStudentTableAkpakoun")
public class Student {
	@Id  // primary key
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "studentId")	
	private Integer studentId;
	@Column (name = "FIRST_NAME")
	private String firstName;
	@Column (name="LAST_NAME")
	private String lastName;
	@Column (name= "EMAIL")
	private String email;
	@Column (name = "CourseIds")
	private String classes;
	
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName= lastName;
		this.email= email;
		this.classes= "";
	}
	

	public Student() {
		super();
	}


	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classIds) {
		this.classes = classIds;
	}
	public void addClass(String courseID) {
		if(this.classes == "") {
			this.classes=courseID;
		}
		else {
		this.classes += "," + (courseID);
		}
	}
//	@Override
//	public String toString() {
//		 String msg =  "Student: " + this.studentId + "Name: "+ this.firstName + " "+ this.lastName +
//				"  Classes enrolled: ";
//		for(int i=0; i< this.classes.size(); i++) {
//			if(i  == this.classes.size()-1) {
//				msg+= this.classes.get(i)+"\n";
//			}
//			msg+= this.classes.get(i) + ", " ;
//		}
//		return msg;
//	}
	
}
