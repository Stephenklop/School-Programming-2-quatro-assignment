package codecademy;

import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidatorTests {

    @Test
    @DisplayName("Invalid Email - Missing \".\" symbol throws exception")
    void testValidateEmailMissingDotThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test@com"));
    }

    @Test
    @DisplayName("Invalid Email - Missing \"@\" symbol throws exception")
    void testValidateEmailMissingAtThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test.com"));
    }

    @Test
    @DisplayName("Invalid Email - Double \"@\" symbol throws exception")
    void testValidateEmailDoubleAtThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateEmail("test@test@com"));
    }

    @Test
    @DisplayName("Valid Emails - Does not throw exceptions")
    void testValidateEmailValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test-test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test153@test.com"));
    }
}