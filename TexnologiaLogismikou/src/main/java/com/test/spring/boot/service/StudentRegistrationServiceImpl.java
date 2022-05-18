package com.test.spring.boot.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.spring.boot.dao.StudentRegistrationDAO;
import com.test.spring.boot.domain.StudentRegistration;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService
{
	@Autowired
	private StudentRegistrationDAO studentRepository;

	public StudentRegistrationServiceImpl() 
	{
		super();
	}
	
	@Autowired
	public StudentRegistrationServiceImpl(StudentRegistrationDAO theStudentRepository) 
	{
		studentRepository = theStudentRepository;
	}
	
	@Override
	@Transactional
	public List<StudentRegistration> findAll() 
	{
		return studentRepository.findAll();
		
	}
	
	@Override
	@Transactional
	public List<StudentRegistration> findRegistrationByCourseId(int courseId) 
	{
		List<StudentRegistration> result = studentRepository.findAll();
		
		
		for(int i =0; i<result.size(); i++)
		{
			StudentRegistration temp = result.get(i);
			int id = temp.getCourseId();
			
			if(id != courseId)
			{
				result.remove(i);
			}
		}
		
		for(int i =0; i<result.size(); i++)
		{
			StudentRegistration temp = result.get(i);
			int id = temp.getCourseId();
			
			if(id != courseId)
			{
				result.remove(i);
			}
		}
		
		for(int i =0; i<result.size(); i++)
		{
			StudentRegistration temp = result.get(i);
			int id = temp.getCourseId();
			
			if(id != courseId)
			{
				result.remove(i);
			}
		}
		return result;

	}

	@Override
	@Transactional
	public void deleteByRegId(int regId) 
	{
		studentRepository.deleteById(regId);
	}

	@Override
	@Transactional
	public void save(StudentRegistration StudentReg) 
	{
		studentRepository.save(StudentReg);
	}

	@Override
	@Transactional
	public StudentRegistration update(int registrationId) 
	{
		StudentRegistration studentReg = studentRepository.findById(registrationId).get();
        return studentReg;
	}


}
