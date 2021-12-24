import com.example.quatroopdracht.domain.View;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewTests {

    @Test
    @DisplayName("Invalid Views")
    void testInvalidViews() {

        // Invalid content reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateView(TestHelper.INVALID_VIEW));

        // Null references
        Assertions.assertThrows(Exception.class, () -> Validator.validateView(new View(
                null,
                null,
                -1
        )));
    }

    @Test
    @DisplayName("Valid View")
    void testValidView() {
        Assertions.assertDoesNotThrow(() -> Validator.validateView(TestHelper.VALID_VIEW));
    }
}
