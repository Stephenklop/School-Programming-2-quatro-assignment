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
    @DisplayName("Invalid Zipcode - One character too long throws exception")
    void testValidateZipcodeTooLongThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateZipcode("2231ADS"));
    }

    @Test
    @DisplayName("Invalid Zipcode - Number in last 2 characters throws exception")
    void testValidateZipcodeInvalidZipEndThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateZipcode("4231L4"));
    }

    @Test
    @DisplayName("Invalid Zipcode - Letter in first 4 digits throws exception")
    void testValidateZipcodeInvalidDigitsThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateZipcode("T323DF"));
    }

    @Test
    @DisplayName("Valid Zipcode - Does not throw exception")
    void testValidateZipcodeValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateZipcode("3241LP"));
    }

    @Test
    @DisplayName("Valid Emails - Does not throw exceptions")
    void testValidateEmailValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test-test@test.com"));

        Assertions.assertDoesNotThrow(() -> Validator.validateEmail("test153@test.com"));
    }
}