import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ValidatorTests {
    private static final Date CURRENT_DATE = new Date();

    @Test
    @DisplayName("Invalid Emails")
    void testInvalidEmails() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test@com"), "Email is invalid.");

        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test.com"), "Email is invalid.");

        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test@test@com"), "Email is invalid.");
    }

    @Test
    @DisplayName("Valid Emails")
    void testValidEmails() {
        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test@test.com"), "Email is invalid.");

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test-test@test.com"), "Email is invalid.");

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test153@test.com"), "Email is invalid.");
    }

    @Test
    @DisplayName("Invalid Students")
    void testInvalidStudents() {

        // Empty address string
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(new Student(
                "person@test.com",
                "Person",
                "Female",
                CURRENT_DATE,
                "",
                "Breda",
                "Netherlands"
        )));

        // Null reference as birthdate
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(new Student(
                "person@test.com",
                "Person",
                "Male",
                null,
                "Lovensdijkstraat 61",
                "Breda",
                "Netherlands"
        )));

        // Invalid email
        Assertions.assertThrows(Exception.class, () -> Validator.validateStudent(new Student(
                "person@test-com",
                "Person",
                "Male",
                CURRENT_DATE,
                "Lovensdijkstraat 61",
                "Breda",
                "Netherlands"
        )));
    }

    @Test
    @DisplayName("Valid Students")
    void testValidStudents() {

        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(new Student(
                "person@test.com",
                "Person",
                "Female",
                CURRENT_DATE,
                "Brick Lane 4",
                "London",
                "United Kingdom"
        )));

        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(new Student(
                "person@test.com",
                "Firstname Lastname",
                "Male",
                CURRENT_DATE,
                "Zandstraat 32",
                "Machelen",
                "België"
        )));

        Assertions.assertDoesNotThrow(() -> Validator.validateStudent(new Student(
                "person@test.com",
                "Test Test Test",
                "Male",
                CURRENT_DATE,
                "Lovensdijkstraat 61",
                "Breda",
                "Netherlands"
        )));
    }
}