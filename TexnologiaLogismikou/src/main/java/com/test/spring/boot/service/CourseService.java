package com.test.spring.boot.service;
import java.util.List;
import com.test.spring.boot.domain.Course;

public interface CourseService {
    public List<Course> findCourseByInstructorLogin(String instructor);
    
    public void deleteById(int courseId);

    public void save(Course theCourse);

    public Course update(int courseId);

    
}
