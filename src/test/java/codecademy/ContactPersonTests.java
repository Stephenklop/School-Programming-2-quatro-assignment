package codecademy;

import com.example.quatroopdracht.domain.ContactPerson;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactPersonTests {

    @Test
    @DisplayName("Invalid Contact Person - Invalid email throws exception")
    void testValidateContactPersonInvalidEmailThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateContactPerson(TestHelper.INVALID_CONTACT_PERSON));
    }

    @Test
    @DisplayName("Invalid Contact Person - Empty string throws exception")
    void testValidateContactPersonEmptyStringThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateContactPerson(new ContactPerson(
                "valid@email.test",
                ""
        )));
    }

    @Test
    @DisplayName("Valid Contact Person - Does not throw exception")
    void testValidateContactPersonValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateContactPerson(TestHelper.VALID_CONTACT_PERSON));
    }
}
