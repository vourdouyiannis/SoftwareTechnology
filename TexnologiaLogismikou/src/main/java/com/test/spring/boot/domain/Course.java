package com.test.spring.boot.domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course" ,schema = "studentregistration")
public class Course 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="instructor")
	private String instructor;
	
	@Column(name="year")
	private int year;
	
	@Column(name="semester")
	private int semester;
	
	@OneToMany(mappedBy = "registrationId",
			cascade = CascadeType.ALL,
			orphanRemoval = false			
			)
	
	private List<StudentRegistration> studentReg;
	
	public Course(int id, String name,String description, String instructor, int year, int semester, List<StudentRegistration> studentReg)
	{
		this.id = id;
		this.name = name;
		this.description =description;
		this.instructor = instructor;
		this.year = year;
		this.semester = semester;
		studentReg = new ArrayList<StudentRegistration>() ;
	}
	
	public Course()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<StudentRegistration> getStudentReg() {
		return studentReg;
	}

	public void setStudentReg(List<StudentRegistration> studentReg) {
		this.studentReg = studentReg;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
	@Override
	public String toString()
	{
		return id +" | "+ name +" | "+ description +" | "+ instructor +" | "+ year +" | "+ semester +" | ";
	}
	

}
