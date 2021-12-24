import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CourseTests {
    @Test
    @DisplayName("Invalid Courses")
    void testInvalidCourses() {

        // Null reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateCourse(TestHelper.INVALID_COURSE));

        // Empty strings
        Assertions.assertThrows(Exception.class, () -> Validator.validateCourse(new Course(
                new ArrayList<>(),
                "",
                "Subject",
                "",
                "Level"
        )));
    }

    @Test
    @DisplayName("Valid Course")
    void testValidCourse() {
        Assertions.assertDoesNotThrow(() -> Validator.validateCourse(TestHelper.VALID_COURSE));
    }
}
