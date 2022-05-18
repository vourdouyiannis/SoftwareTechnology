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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.spring.boot.domain.StudentRegistration;
import com.test.spring.boot.service.StudentRegistrationService;



@Controller
public class StudentRegMgtAppController {
	
	@Autowired
	private StudentRegistrationService studentService;
	
	private int courseIdGlobal ;
	
	public StudentRegMgtAppController(StudentRegistrationService studentService)
	{
		this.studentService = studentService;
	}

	@GetMapping("/listStudents")
	public String listStudents(@RequestParam("courseId") int theId ,Model theModel) 
	{
		courseIdGlobal = theId;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		List<StudentRegistration> theStudentRegistrations = studentService.findRegistrationByCourseId(theId);
		
		for(int i =0; i<theStudentRegistrations.size(); i++)
		{
			theStudentRegistrations.get(i).calculateFinalGrade();
		}
		
		theModel.addAttribute("StudentRegistrations", theStudentRegistrations);

		
		return "registration/list-studentRegs";
	}
	
	@GetMapping("/showFormForAddStudentRegistrations")
	public String showFormForAddStudentRegistrations( Model theModel) 
	{
		
		StudentRegistration theStudentRegistration = new StudentRegistration();
		
		theModel.addAttribute("StudentRegistrations", theStudentRegistration);
		
		return "registration/studentReg-form";
	}	
	
	@GetMapping("/showFormForUpdateStudentRegistrations")
	public String showFormForUpdateStudentRegistrations(@RequestParam("registrationId") int registrationId, Model theModel)
	{
		
		StudentRegistration theStudentRegistration = studentService.update(registrationId); 
		
		theModel.addAttribute("StudentRegistrations", theStudentRegistration); 
		
		return "registration/studentReg-form";
	} 
	
	@RequestMapping("/saveStudentRegistrations")
	public String saveStudentRegistration(@ModelAttribute("StudentRegistrations") StudentRegistration theStudentRegistration, Model theModel, RedirectAttributes redirectAttributes) {
	
		
		theStudentRegistration.setCourseId(courseIdGlobal);

		studentService.save(theStudentRegistration);
		
		redirectAttributes.addAttribute("courseId", theStudentRegistration.getCourseId());
		
		
		return "redirect:/listStudents";
		
	}	

	@GetMapping("/deleteStudentRegistrations")
	public String deleteStudentRegistration(@RequestParam("registrationId") int theId, RedirectAttributes redirectAttributes)
	{
		StudentRegistration temp = studentService.update(theId);
		int courseId = temp.getCourseId();
		
		studentService.deleteByRegId(theId);
		

		redirectAttributes.addAttribute("courseId", courseId);
		
		
		return "redirect:/listStudents";
		
	}	
}
