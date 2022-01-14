package codecademy;

import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTests {

    @Test
    @DisplayName("Invalid Student - Null reference throws exception")
    void testValidateStudentNullThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(TestHelper.INVALID_STUDENT));
    }

    @Test
    @DisplayName("Invalid Student - Empty string throws exception")
    void testValidateStudentEmptyStringThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(new Student(
                "person@test.com",
                "Person",
                "Female",
                TestHelper.CURRENT_DATE,
                "",
                "Breda",
                "Netherlands"
        )));
    }

    @Test
    @DisplayName("Invalid Student - Invalid email throws exception")
    void testValidateStudentInvalidEmailThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(new Student(
                "person@test-com",
                "Person",
                "Male",
                TestHelper.CURRENT_DATE,
                "Lovensdijkstraat 61",
                "Breda",
                "Netherlands"
        )));
    }

    @Test
    @DisplayName("Valid Students - Does not throw exceptions")
    void testValidateStudentValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(TestHelper.VALID_STUDENT));

        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(new Student(
                "person@test.com",
                "Firstname Lastname",
                "Male",
                TestHelper.CURRENT_DATE,
                "Zandstraat 32",
                "Machelen",
                "België"
        )));

        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(new Student(
                "person@test.com",
                "Test Test Test",
                "Male",
                TestHelper.CURRENT_DATE,
                "Lovensdijkstraat 61",
                "Breda",
                "Netherlands"
        )));
    }
}
