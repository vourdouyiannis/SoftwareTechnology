package TestDAO;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.test.spring.boot.SecureCourseApplication;
import com.test.spring.boot.dao.CourseDAO;
import com.test.spring.boot.domain.Course;

@SpringBootTest(classes = {SecureCourseApplication.class,CourseDAO.class, Course.class})
@TestPropertySource(locations = "classpath:application.properties")
public class TestCourseDAO
{
	@Autowired 
	CourseDAO courseDAO ;
	
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}

	@Test
	void testFindByIdReturnsCourse() {
		
		Course storedCourse = courseDAO.getById(301);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("Physics", storedCourse.getName());
	}
}
