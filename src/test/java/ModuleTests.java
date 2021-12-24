import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ModuleTests {
    @Test
    @DisplayName("Invalid Modules")
    void testInvalidModules() {

        // Invalid course reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(TestHelper.INVALID_MODULE));

        // Invalid contact person reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(new Module(
                0,
                TestHelper.CURRENT_DATE,
                "Status",
                "Title",
                0,
                "Description",
                TestHelper.VALID_COURSE,
                TestHelper.INVALID_CONTACT_PERSON,
                "SerialNumber",
                0
        )));

        // Null reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateContent(new Module(
                0,
                null,
                "Status",
                "Title",
                0,
                "Description",
                TestHelper.VALID_COURSE,
                TestHelper.VALID_CONTACT_PERSON,
                "SerialNumber",
                0
        )));
    }

    @Test
    @DisplayName("Valid Module")
    void testValidModule() {
        Assertions.assertDoesNotThrow(() -> Validator.validateContent(TestHelper.VALID_MODULE));
    }
}
