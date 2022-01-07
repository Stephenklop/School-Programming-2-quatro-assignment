package codecademy;

import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebcastTests {

    @Test
    @DisplayName("Invalid Webcasts")
    void testInvalidWebcasts() {

        // Empty strings
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(TestHelper.INVALID_WEBCAST));

        // Null reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(new Webcast(
                0,
                TestHelper.CURRENT_DATE,
                "Status",
                "Title",
                0,
                "Description",
                "SpeakerName",
                null
        )));
    }

    @Test
    @DisplayName("Valid Webcast")
    void testValidWebcast() {
        Assertions.assertDoesNotThrow(() -> Validator.validateContent(TestHelper.VALID_WEBCAST));
    }
}
