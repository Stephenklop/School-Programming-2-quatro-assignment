import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidatorTests {

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
        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test-test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test153@test.com"));
    }
}