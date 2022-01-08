package codecademy;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CourseTests {

    @Test
    @DisplayName("Invalid Course - Null reference throws exception")
    void testValidateCourseNullThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateCourse(TestHelper.INVALID_COURSE));
    }

    @Test
    @DisplayName("Invalid Course - Empty string throws exception")
    void testValidateCourseEmptyStringThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateCourse(new Course(
                new ArrayList<>(),
                "",
                "Subject",
                "",
                "Level"
        )));
    }

    @Test
    @DisplayName("Valid Course - Does not throw exception")
    void testValidateCourseValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateCourse(TestHelper.VALID_COURSE));
    }
}
