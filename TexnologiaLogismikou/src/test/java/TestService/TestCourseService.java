package TestService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.test.spring.boot.SecureCourseApplication;
import com.test.spring.boot.dao.CourseDAO;
import com.test.spring.boot.domain.Course;
import com.test.spring.boot.service.CourseService;

@SpringBootTest(classes = {SecureCourseApplication.class,CourseService.class, Course.class, CourseDAO.class})
@TestPropertySource(locations = "classpath:application.properties")
class TestCourseService {

	@Autowired 
	CourseService courseService;
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseService);
	}

	@Test
	void testFindByIdReturnsCourse() 
	{
		Course storedCourse = courseService.update(301);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("nikolaidis", storedCourse.getInstructor());
	}
}