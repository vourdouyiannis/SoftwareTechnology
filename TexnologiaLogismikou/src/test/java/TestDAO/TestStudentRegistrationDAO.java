package TestDAO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.test.spring.boot.SecureCourseApplication;
import com.test.spring.boot.dao.StudentRegistrationDAO;
import com.test.spring.boot.domain.StudentRegistration;


@SpringBootTest(classes = {SecureCourseApplication.class,StudentRegistrationDAO.class, StudentRegistration.class})
@TestPropertySource(locations = "classpath:application.properties")
public class TestStudentRegistrationDAO
{
	@Autowired 
	StudentRegistrationDAO studentDAO ;
	
	@Test
	void testStudentRegistrationJpaImplIsNotNull() {
		Assertions.assertNotNull(studentDAO);
	}

	@Test
	void testFindByIdReturnsStudentRegistration() {
		StudentRegistration storedStudent = studentDAO.getById(1);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("Maria", storedStudent.getName());
	}
}