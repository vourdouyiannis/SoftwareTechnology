package TestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.test.spring.boot.SecureCourseApplication;
import com.test.spring.boot.controller.StudentRegMgtAppController;
import com.test.spring.boot.dao.StudentRegistrationDAO;
import com.test.spring.boot.domain.StudentRegistration;
import com.test.spring.boot.service.StudentRegistrationService;

@SpringBootTest(classes = {SecureCourseApplication.class,StudentRegistrationService.class, StudentRegistration.class, StudentRegistrationDAO.class, StudentRegMgtAppController.class})
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestStudentRegMgtAppController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	StudentRegMgtAppController studentRegController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testStudentRegMgtAppControllerIsNotNull() {
		Assertions.assertNotNull(studentRegController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "georgiadou")
	@Test 
	void testListStudentsReturnsPage() throws Exception {
		mockMvc.perform(get("/listStudents").
		param("courseId", "301")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("StudentRegistrations")).
		andExpect(view().name("registration/list-studentRegs"));		
		
	}

	@WithMockUser(value = "nikolaidis")
	@Test 
	void testSaveStudentRegistrationReturnsPage() throws Exception {
		
	    StudentRegistration StudentRegistrations = new StudentRegistration(12, 3421, "Marios", "Karagiannis", 2025, 5, 0, 0, 0);	    	    
	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("registrationId", Integer.toString(StudentRegistrations.getRegistrationId()));
	    multiValueMap.add("studentId", Integer.toString(StudentRegistrations.getStudentId()));
	    multiValueMap.add("courseId", Integer.toString(StudentRegistrations.getCourseId()));
	    multiValueMap.add("name", StudentRegistrations.getName());
	    multiValueMap.add("lastName", StudentRegistrations.getLastName());
	    multiValueMap.add("courseRegYear", Integer.toString(StudentRegistrations.getCourseRegYear()));
	    multiValueMap.add("studentSemester", Integer.toString(StudentRegistrations.getStudentSemester()));
	    multiValueMap.add("finalExamGrade", Integer.toString(StudentRegistrations.getFinalExamGrade()));
	    multiValueMap.add("projectGrade", Integer.toString(StudentRegistrations.getProjectGrade()));
	    multiValueMap.add("overallGrade", Float.toString(StudentRegistrations.getOverallGrade()));
	    
		mockMvc.perform(
				get("/saveStudentRegistrations").
			    params(multiValueMap)).
				andExpect(status().isFound()).
				andExpect(view().name("redirect:/listStudents"));	
	}
	
	
}