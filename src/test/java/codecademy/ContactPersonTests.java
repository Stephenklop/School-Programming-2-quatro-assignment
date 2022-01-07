package codecademy;

import com.example.quatroopdracht.domain.ContactPerson;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactPersonTests {
    @Test
    @DisplayName("Invalid Contact Persons")
    void testInvalidContactPersons() {

        // Invalid email
        Assertions.assertThrows(Exception.class, () -> Validator.validateContactPerson(TestHelper.INVALID_CONTACT_PERSON));

        // Empty string
        Assertions.assertThrows(Exception.class, () -> Validator.validateContactPerson(new ContactPerson(
                "valid@email.test",
                ""
        )));
    }

    @Test
    @DisplayName("Valid Contact Person")
    void testValidContactPerson() {
        Assertions.assertDoesNotThrow(() -> Validator.validateContactPerson(TestHelper.VALID_CONTACT_PERSON));
    }
}
