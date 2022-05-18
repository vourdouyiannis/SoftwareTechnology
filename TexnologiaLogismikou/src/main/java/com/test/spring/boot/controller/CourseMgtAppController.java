package com.test.spring.boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.test.spring.boot.domain.Course;
import com.test.spring.boot.service.CourseService;

@Controller
public class CourseMgtAppController 
{
	@Autowired
	private  CourseService courseService;
	
	
	public CourseMgtAppController(CourseService courseService)
	{
		this.courseService = courseService;
	}
	
	
	@GetMapping("/listCourses")
	public String listCourses(Model theModel) 
	{	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println(currentPrincipalName);
		
		List<Course> theCourses = courseService.findCourseByInstructorLogin(currentPrincipalName);

		theModel.addAttribute("courses", theCourses);
		
		return "registration/list-courses";
	}
	
	@GetMapping("/showFormForAddCourse")
	public String showFormForAddCourse(Model theModel) 
	{
		Course theCourse = new Course();
		
		theModel.addAttribute("course", theCourse);
		
		return "registration/course-form";
	}	
	
	@GetMapping("/showFormForUpdateCourse")
	public String showFormForUpdateCourse(@RequestParam("courseId") int courseId, Model theModel)
	{	
		List<Course> theCourses = (List<Course>) theModel.getAttribute("courses");
		
		Course theCourse = courseService.update(courseId);
		
		theModel.addAttribute("course", theCourse);
		
		return "registration/course-form";
	}
	
	@RequestMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course theCourse, Model theModel) {
	
		courseService.save(theCourse);
				
		return "redirect:/listCourses";
		
	}	

	@GetMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		
		courseService.deleteById(theId);
		
		return "redirect:/listCourses";
		
	}		
	
	
	
}
