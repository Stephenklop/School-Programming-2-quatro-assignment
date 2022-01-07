import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PostalCodeTest {
    /**
     * @subcontract null postalCode
     * @requires postalCode == null;
     * @signals (NullPointerException) postalCode == null;
     */

    @Test(expected = NullPointerException.class)
    @DisplayName("Null pointer exception - Postalcode null returns exception")
    public void testGetFormattedPostalCodeRequiresPostalCodeNullNullPointerException() {
        // Arrange
        String unformattedPostalCode = null;

        // Act
        String result = PostalCode.formatPostalCode(unformattedPostalCode);
    }

    /**
     * @subcontract invalid postalCode
     * @requires no other valid precondition
     * @signals (IllegalArgumentException);
     */

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Illegal argument exception - Postalcode 29516C returns exception")
    public void testGetFormattedPostalCodeIllegalArgumentException() {
        // Arrange
        String unformattedPostalCode = "29516C";

        // Act
        String result = PostalCode.formatPostalCode(unformattedPostalCode);
    }

    /**
     * @subcontract valid postalCode {
     * @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *           Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *           postalCode.trim().substring(4).trim().length == 2 &&
     *           'A' <=
     *           postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <=
     *           'Z' &&
     *           'A' <=
     *           postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <=
     *           'Z';
     * @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *          postalCode.trim().substring(4).trim().toUpperCase()
     *          }
     */

    @Test()
    @DisplayName("2951bc returns 2951 BC")
    public void testGetFormattedPostalCodeRequiresValidPostalCodeEnsuresFormattedPostalCode() {
        // Arrange
        String unformattedPostalCode = "2951bc";

        // Act
        String result = PostalCode.formatPostalCode(unformattedPostalCode);

        // Assert
        assertEquals("2951 BC", result);
    }
}
