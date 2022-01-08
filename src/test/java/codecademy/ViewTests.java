package codecademy;

import com.example.quatroopdracht.domain.View;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewTests {

    @Test
    @DisplayName("Invalid View - Invalid content throws exception")
    void testValidateViewInvalidContentThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateView(TestHelper.INVALID_VIEW));
    }

    @Test
    @DisplayName("Invalid View - Null reference throws exception")
    void testValidateViewNullThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateView(new View(
                null,
                null,
                -1
        )));
    }

    @Test
    @DisplayName("Valid View - Does not throw exception")
    void testValidateViewValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateView(TestHelper.VALID_VIEW));
    }
}