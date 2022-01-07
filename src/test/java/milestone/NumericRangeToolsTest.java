package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumericRangeToolsTest {

    @Test
    @DisplayName("Percentage Validation - 101% is invalid")
    void testIsValidPercentage101ReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(101));
    }

    @Test
    @DisplayName("Percentage Validation - -1% is invalid")
    void testIsValidPercentageNegativeReturnsFalse() {
        Assertions.assertFalse(NumericRangeTools.isValidPercentage(-1));
    }

    @Test
    @DisplayName("Percentage Validation - 0% is valid")
    void testIsValidPercentage0ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(0));
    }

    @Test
    @DisplayName("Percentage Validation - 100% is valid")
    void testIsValidPercentage100ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(100));
    }

    @Test
    @DisplayName("Percentage Validation - 50% is valid")
    void testIsValidPercentage50ReturnsTrue() {
        Assertions.assertTrue(NumericRangeTools.isValidPercentage(50));
    }
}