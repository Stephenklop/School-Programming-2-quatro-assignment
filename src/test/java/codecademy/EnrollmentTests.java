package codecademy;

import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTests {

    @Test
    @DisplayName("Invalid Enrollment - Invalid certificate throws exception")
    void testValidateEnrollmentInvalidCertificateThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(TestHelper.INVALID_ENROLLMENT));
    }

    @Test
    @DisplayName("Invalid Enrollment - Invalid course throws exception")
    void testValidateEnrollmentInvalidCourseThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(new StudentEnrollment(
                TestHelper.VALID_STUDENT,
                TestHelper.INVALID_COURSE,
                TestHelper.VALID_CERTIFICATE,
                TestHelper.CURRENT_DATE
        )));
    }

    @Test
    @DisplayName("Invalid Enrollment - Invalid student throws exception")
    void testValidateEnrollmentInvalidStudentThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEnrollment(new StudentEnrollment(
                TestHelper.INVALID_STUDENT,
                TestHelper.VALID_COURSE,
                TestHelper.VALID_CERTIFICATE,
                TestHelper.CURRENT_DATE
        )));
    }

    @Test
    @DisplayName("Valid Enrollment - Does not throw exception")
    void testValidateEnrollmentValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateEnrollment(TestHelper.VALID_ENROLLMENT));
    }
}
