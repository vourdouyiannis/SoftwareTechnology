package com.test.spring.boot.service;
import java.util.List;

import com.test.spring.boot.domain.StudentRegistration;

public interface StudentRegistrationService 
{
	public List<StudentRegistration> findRegistrationByCourseId(int courseId);
	
	public List<StudentRegistration> findAll();
	
	public void deleteByRegId(int regId);
	
	public void save(StudentRegistration StudentReg);
	
	public StudentRegistration update(int registrationId);

}
