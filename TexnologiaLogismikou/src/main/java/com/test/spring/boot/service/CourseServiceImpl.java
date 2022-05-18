package com.test.spring.boot.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.dao.CourseDAO;
import com.test.spring.boot.domain.Course;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseRepository;
 

    public CourseServiceImpl() {
        super();
    }
    
    @Autowired
    public CourseServiceImpl(CourseDAO theCourseRepository) {
    	courseRepository = theCourseRepository;
    }


    @Override
    @Transactional
    public List<Course> findCourseByInstructorLogin(String instructor) {
        List<Course> result = courseRepository.findAll();
        Course temp;
        String name;
        
        //In case a user has no courses:
        //For strange reason in the for loop some courses do not appear
        //in the list result while outside of the for loop they exist
        //We find a temporary solution with three for loops and now it works
        for(int i=0; i<result.size(); i++)
        {
        	
        	temp = result.get(i);
        	
        	name = temp.getInstructor();
        	
        	if(name.equals(instructor) == false)
        	{
        		result.remove(i);

        	}
        	
        }
        
        for(int i=0; i<result.size(); i++)
        {
        	
        	temp = result.get(i);
        	
        	name = temp.getInstructor();
        	
        	if(name.equals(instructor) == false)
        	{
        		result.remove(i);

        	}
        	
        }
        
        for(int i=0; i<result.size(); i++)
        {
        	
        	temp = result.get(i);
        	
        	name = temp.getInstructor();
        	
        	if(name.equals(instructor) == false)
        	{
        		result.remove(i);

        	}
        	
        }
        

        return result;
     
    }
    
    @Override
    @Transactional
    public Course update(int courseId) {
        Course course = courseRepository.findById(courseId).get();
        return course;
    }

    @Override
    @Transactional
    public void deleteById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        courseRepository.save(theCourse);
    }


}

