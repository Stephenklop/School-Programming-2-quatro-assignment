package codecademy;

import com.example.quatroopdracht.domain.Certificate;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CertificateTests {
    @Test
    @DisplayName("Invalid Certificates")
    void testInvalidCertificates() {

        // Empty string
        Assertions.assertThrows(Exception.class, () -> Validator.validateCertificate(TestHelper.INVALID_CERTIFICATE));

        // Null reference
        Assertions.assertThrows(Exception.class, () -> Validator.validateCertificate(new Certificate(
                0,
                0,
                null
        )));
    }

    @Test
    @DisplayName("Valid Certificate")
    void testValidCertificate() {
        Assertions.assertDoesNotThrow(() -> Validator.validateCertificate(TestHelper.VALID_CERTIFICATE));
    }
}
