package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateToolsTest {

    /**
     * Out of bounds arguments, such as:
     * - Day 0
     * - Month 0
     * - Month 13
     * Returns false
     */

    @Test
    @DisplayName("Out of bounds - Month 0 returns false")
    void testValidateDateMonth0ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(1, 0, 0));
    }

    @Test
    @DisplayName("Out of bounds - Day 0 returns false")
    void testValidateDateDay0ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(1, 0, 0));
    }

    @Test
    @DisplayName("Out of bounds - Month 13 returns false")
    void testValidateDateMonth13ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(1, 13, 0));
    }

    /**
     * Passing values for days that are too high for the given month.
     * Returns false
     */

    @Test
    @DisplayName("Month (31) - Day 32 returns false")
    void testValidateDateDay32ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(32, 1, 2020));
    }

    @Test
    @DisplayName("Month (30) - Day 31 returns false")
    void testValidateDateDay31ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(31, 11, 2020));
    }

    @Test
    @DisplayName("Month (29) - Day 30 returns false")
    void testValidateDateDay30ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(30, 2, 2020));
    }

    @Test
    @DisplayName("Month (28) - Day 29 returns false")
    void testValidateDateDay29ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(29, 2, 2021));
    }

    /**
     * Passing values for days that are equal to the last day of the given month.
     * Returns true
     */

    @Test
    @DisplayName("Month (31) - Day 31 returns true")
    void testValidateDateDay31ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(31, 1, 2020));
    }

    @Test
    @DisplayName("Month (30) - Day 31 returns true")
    void testValidateDateDay30ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(30, 11, 2020));
    }

    @Test
    @DisplayName("Month (29) - Day 30 returns true")
    void testValidateDateDay29ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(29, 2, 2020));
    }

    @Test
    @DisplayName("Month (28) - Day 29 returns true")
    void testValidateDateDay28ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(28, 2, 2021));
    }
}