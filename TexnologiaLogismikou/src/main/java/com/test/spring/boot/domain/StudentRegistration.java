package com.test.spring.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="studentregistration")
public class StudentRegistration 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="registrationID")
	private int registrationId;

	@Column(name="StudentID")
	private int studentId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="courseRegYear")
	private int courseRegYear;
	
	@Column(name="StudentSemester")
	private int studentSemester;
	
	@Column(name = "finalExamGrade")
	private int finalExamGrade;
	
	@Column(name = "projectGrade")
	private int projectGrade;

	@Column(name = "CourseID")
	private int courseId;
	
	@Column(name = "overallGrade")
	private float overallGrade;
	
	public StudentRegistration(int registrationId, int studentId, String name, String lastName, int courseRegYear,
			int studentSemester, int courseId, int finalExamGrade, int projectGrade, float overallGrade) 
	{
		super();
		this.overallGrade = overallGrade;
		this.finalExamGrade = finalExamGrade;
		this.projectGrade = projectGrade;
		this.registrationId = registrationId;
		this.studentId = studentId;
		this.name = name;
		this.lastName = lastName;
		this.courseRegYear = courseRegYear;
		this.studentSemester = studentSemester;
		this.courseId = courseId;
	}
	
	public StudentRegistration(int registrationId, int studentId, String name, String lastName, int courseRegYear,
			int studentSemester,  int finalExamGrade, int projectGrade, float overallGrade) 
	{
		super();
		this.overallGrade = overallGrade;
		this.finalExamGrade = finalExamGrade;
		this.projectGrade = projectGrade;
		this.registrationId = registrationId;
		this.studentId = studentId;
		this.name = name;
		this.lastName = lastName;
		this.courseRegYear = courseRegYear;
		this.studentSemester = studentSemester;
	}

	
	public StudentRegistration() {
	}
	
	
	public float calculateFinalGrade()
	{
		overallGrade = (float) ((0.7*finalExamGrade) + (0.3*projectGrade));
		return overallGrade;
	}
	
	
	public float getOverallGrade() {
		return overallGrade;
	}


	public void setOverallGrade(float overallGrade) {
		this.overallGrade = overallGrade;
	}


	public int getFinalExamGrade() {
		return finalExamGrade;
	}


	public void setFinalExamGrade(int finalExamGrade) {
		this.finalExamGrade = finalExamGrade;
	}


	public int getProjectGrade() {
		return projectGrade;
	}


	public void setProjectGrade(int projectGrade) {
		this.projectGrade = projectGrade;
	}


	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getCourseRegYear() {
		return courseRegYear;
	}

	public void setCourseRegYear(int courseRegYear) {
		this.courseRegYear = courseRegYear;
	}
	
	public int getStudentSemester() {
		return studentSemester;
	}

	public void setStudentSemester(int studentSemester) {
		this.studentSemester = studentSemester;
	}
	
	
	
	@Override
	public String toString()
	{
		return studentId +" | "+ registrationId + " | "+ name+ " | "+ lastName +" | "+ courseRegYear +" | "+ studentSemester;
	}
	


}
