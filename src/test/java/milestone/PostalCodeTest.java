package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostalCodeTest {

    /**
     * @subcontract null postalCode {
     *   @requires postalCode == null;
     *   @signals (NullPointerException) postalCode == null;
     * }
     */
    @Test
    @DisplayName("Null pointer exception - Postalcode null returns exception")
    void testFormatPostalCodeRequiresNullSignalsNullPointerException() {
        // Arrange
        String unformattedPostalCode = null;

        // Assert
        Assertions.assertThrows(NullPointerException.class, () -> PostalCode.formatPostalCode(unformattedPostalCode));
    }

    /**
     * @subcontract invalid postalCode {
     *   @requires no other valid precondition;
     *   @signals (IllegalArgumentException);
     * }
     */
    @Test
    @DisplayName("Illegal argument exception - Postalcode 29516C returns exception")
    void testFormatPostalCodeSignalsIllegalArgumentException() {
        // Arrange
        String unformattedPostalCode = "29516C";

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> PostalCode.formatPostalCode(unformattedPostalCode));
    }

    /**
     * @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
     */
    @Test
    @DisplayName("2951bc returns 2951 BC")
    void testFormatPostalCodeRequiresValidPostalCodeEnsuresFormattedPostalCode() {
        // Arrange
        String unformattedPostalCode = "2951bc";

        // Act
        String result = PostalCode.formatPostalCode(unformattedPostalCode);

        // Assert
        Assertions.assertEquals("2951 BC", result);
    }
}
