package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumericRangeToolsTest {

    /**
     * @subcontract value out of range high {
     *  @requires percentage > 100;
     *  @signals \result = false;
     * }
     */
    @Test
    @DisplayName("Out of range (high) - 101% is invalid")
    void testIsValidPercentage101ReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(101));
    }

    @Test
    @DisplayName("Out of range (high) - 1000% is invalid")
    void testIsValidPercentage1000ReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(1000));
    }

    /**
     * @subcontract value out of range low {
     *  @requires percentage < 0;
     *  @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("Out of range (low) - -1% is invalid")
    void testIsValidPercentageNegative1ReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(-1));
    }

    @Test
    @DisplayName("Out of range (low) - -100% is invalid")
    void testIsValidPercentageNegative100ReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(-100));
    }

    /**
     * @subcontract value within valid range {
     *  @requires 0 <= percentage <= 100;
     *  @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("Valid percentage - 0% is valid")
    void testIsValidPercentage0ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(0));
    }

    @Test
    @DisplayName("Valid percentage - 100% is valid")
    void testIsValidPercentage100ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(100));
    }

    @Test
    @DisplayName("Valid percentage - 50% is valid")
    void testIsValidPercentage50ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(50));
    }
}