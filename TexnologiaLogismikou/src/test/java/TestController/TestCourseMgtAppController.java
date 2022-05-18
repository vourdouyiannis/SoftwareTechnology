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
import com.test.spring.boot.controller.CourseMgtAppController;
import com.test.spring.boot.dao.CourseDAO;
import com.test.spring.boot.domain.Course;
import com.test.spring.boot.service.CourseService;

@SpringBootTest(classes = {SecureCourseApplication.class,CourseService.class, Course.class, CourseDAO.class, CourseMgtAppController.class})
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestCourseMgtAppController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CourseMgtAppController courseController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCourseMgtAppControllerIsNotNull() {
		Assertions.assertNotNull(courseController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "nikolaidis")
	@Test 
	void testListCoursesReturnsPage() throws Exception {
		mockMvc.perform(get("/listCourses")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("courses")).
		andExpect(view().name("registration/list-courses"));		
		
		/*
		 * A way to check stuff that are in the model
		 * andExpect(MockMvcResultMatchers.model().attribute("msg", "Hi there, Joe."))
		 */
	}

	@WithMockUser(value = "nikolaidis")
	@Test 
	void testSaveCourseReturnsPage() throws Exception {
		
	    Course course = new Course(502, "Chemistry", "Organic", "nikolaidis", 2025, 5, null);	    
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("description", course.getDescription());
	    multiValueMap.add("instructor", course.getInstructor());
	    multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester", Integer.toString(course.getSemester()));
	    
		mockMvc.perform(
				post("/saveCourse").
			    params(multiValueMap)).
				andExpect(status().isFound()).
				andExpect(view().name("redirect:/listCourses"));	
	}
	
	
}