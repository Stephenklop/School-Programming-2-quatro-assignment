package codecademy;

import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebcastTests {

    @Test
    @DisplayName("Invalid Content - Empty string throws exception")
    void testValidateContentEmptyStringThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(TestHelper.INVALID_WEBCAST));
    }

    @Test
    @DisplayName("Invalid Content - Null reference throws exception")
    void testValidateContentNullThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(new Webcast(
                0,
                TestHelper.CURRENT_DATE,
                "Status",
                "Description",
                "Title",
                "0",
                "Description",
                "SpeakerName",
                null
        )));
    }

    @Test
    @DisplayName("Valid Webcast - Does not throw exception")
    void testValidateContentValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateContent(TestHelper.VALID_WEBCAST));
    }
}
