import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTests {
    @Test
    @DisplayName("Invalid Enrollments")
    void testInvalidEnrollments() {

        // Invalid certificate reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(TestHelper.INVALID_ENROLLMENT));

        // Invalid course reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(new StudentEnrollment(
                TestHelper.VALID_STUDENT,
                TestHelper.INVALID_COURSE,
                TestHelper.VALID_CERTIFICATE,
                TestHelper.CURRENT_DATE
        )));

        // Invalid student reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(new StudentEnrollment(
                TestHelper.INVALID_STUDENT,
                TestHelper.VALID_COURSE,
                TestHelper.VALID_CERTIFICATE,
                TestHelper.CURRENT_DATE
        )));
    }

    @Test
    @DisplayName("Valid Enrollment")
    void testValidEnrollment() {
        Assertions.assertDoesNotThrow(() -> Validator.validateEnrollment(TestHelper.VALID_ENROLLMENT));
    }
}
