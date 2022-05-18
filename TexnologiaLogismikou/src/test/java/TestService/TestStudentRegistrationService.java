package TestService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.test.spring.boot.SecureCourseApplication;
import com.test.spring.boot.dao.CourseDAO;
import com.test.spring.boot.dao.StudentRegistrationDAO;
import com.test.spring.boot.domain.Course;
import com.test.spring.boot.domain.StudentRegistration;
import com.test.spring.boot.service.CourseService;
import com.test.spring.boot.service.StudentRegistrationService;

@SpringBootTest(classes = {SecureCourseApplication.class,StudentRegistrationService.class, StudentRegistration.class, StudentRegistrationDAO.class})
@TestPropertySource(locations = "classpath:application.properties")
class TestStudentRegistrationService {

	@Autowired 
	StudentRegistrationService studentService;
	
	@Test
	void testStudentRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(studentService);
	}

	@Test
	void testFindByIdReturnsCourse() 
	{
		StudentRegistration storedStudent = studentService.update(2);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("Giorgos", storedStudent.getName());
		Assertions.assertEquals("Papadopoulos", storedStudent.getLastName());
	}
}