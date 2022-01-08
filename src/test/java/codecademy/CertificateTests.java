package codecademy;

import com.example.quatroopdracht.domain.Certificate;
import com.example.quatroopdracht.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CertificateTests {

    @Test
    @DisplayName("Invalid Certificate - Empty string throws exception")
    void testValidateCertificateEmptyStringThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateCertificate(TestHelper.INVALID_CERTIFICATE));
    }

    @Test
    @DisplayName("Invalid Certificate - Null reference throws exception")
    void testValidateCertificateNullThrowsException() {
        Assertions.assertThrows(Exception.class, () -> Validator.validateCertificate(new Certificate(
                0,
                0,
                null
        )));
    }

    @Test
    @DisplayName("Valid Certificate - Does not throw exception")
    void testValidateCertificateValidDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> Validator.validateCertificate(TestHelper.VALID_CERTIFICATE));
    }
}
