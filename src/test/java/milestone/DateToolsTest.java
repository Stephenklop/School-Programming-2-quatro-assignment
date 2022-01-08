package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateToolsTest {

    /**
     * @subcontract all other cases {
     *   @requires no other accepting precondition;
     *   @ensures \result = false;
     * }
     *
     * These include out of bounds arguments, such as:
     * - Day 0
     * - Month 0
     * - Month 13
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
     * @subcontract 31 days in month {
     *   @requires (month == 1 || month == 3 || month == 5 || month == 7 ||
     *             month == 8 || month == 10 || month == 12) && 1 <= day <= 31;
     *   @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("Month (31) - Day 32 returns false")
    void testValidateDateDay32ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(32, 1, 2020));
    }

    @Test
    @DisplayName("Month (31) - Day 31 returns true")
    void testValidateDateDay31ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(31, 1, 2020));
    }

    /**
     * @subcontract 30 days in month {
     *   @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
     *              1 <= day <= 30;
     *   @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("Month (30) - Day 31 returns false")
    void testValidateDateDay31ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(31, 11, 2020));
    }

    @Test
    @DisplayName("Month (30) - Day 30 returns true")
    void testValidateDateDay30ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(30, 11, 2020));
    }

    /**
     * @subcontract 29 days in month {
     *   @requires month == 2 && 1 <= day <= 29 &&
     *             (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
     *   @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("Month (29) - Day 30 returns false")
    void testValidateDateDay30ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(30, 2, 2020));
    }

    @Test
    @DisplayName("Month (29) - Day 29 returns true")
    void testValidateDateDay29ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(29, 2, 2020));
    }

    /**
     * @subcontract 28 days in month {
     *   @requires month == 2 && 1 <= day <= 28 &&
     *             (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
     *   @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("Month (28) - Day 29 returns false")
    void testValidateDateDay29ReturnsFalse() {
        Assertions.assertFalse(DateTools.validateDate(29, 2, 2021));
    }

    @Test
    @DisplayName("Month (28) - Day 28 returns true")
    void testValidateDateDay28ReturnsTrue() {
        Assertions.assertTrue(DateTools.validateDate(28, 2, 2021));
    }
}