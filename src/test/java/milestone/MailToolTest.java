package milestone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MailToolTest {

    /**
     * @subcontract local-subdomain delimiter {
     *   @requires !mailAddress.contains("@")
     *   @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("mailAddress contains '@' is valid")
    void testIsValidMailAddressWithAddressSignReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress !contains '@' is invalid")
    void testIsValidMailAddressWithoutAddressSignReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local-subdomain.tld"));
    }

    /**
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("mailAddress contains local is valid")
    void testIsValidMailAddressWithLocalReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress local.length = 1 is valid")
    void testIsValidMailAddressWithOneLengthLocalReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("l@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress local.length = 0 is invalid")
    void testIsValidMailAddressWithoutLocalReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("@subdomain.tld"));
    }

    /**
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("mailAddress contains subdomain && contains tld is valid")
    void testIsValidEmailAddressWithSubdomainWithTldReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress contains subdomain && !contains tld is invalid")
    void testIsValidEmailAddressWithSubdomainWithoutTldReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@subdomain."));
    }

    @Test
    @DisplayName("mailAddress !contains subdomain && contains tld is invalid")
    void testIsValidEmailAddressWithoutSubdomainWithTldReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@.tld"));
    }

    @Test
    @DisplayName("mailAddress !contains subdomain && !contains tld is invalid")
    void testIsValidEmailAddressWithoutSubdomainWithoutTldReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@."));
    }

    @Test
    @DisplayName("mailAddress contains subdomain > 1 && contains tld is invalid")
    void testIsValidEmailAddressWithMultipleSubdomainWithTldReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@subdomain.subdomain.tld"));
    }

    /**
     * @subcontract no subdomain part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[0].length < 1;
     *   @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("mailAddress contains subdomain is valid")
    void testIsValidEmailAddressWithSubdomainReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress subdomain.length = 1 is valid")
    void testIsValidEmailAddressWithOneLengthSubdomainReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@s.tld"));
    }

    @Test
    @DisplayName("mailAddress subdomain.length = 0 is invalid")
    void testIsValidEmailAddressWithoutSubdomainReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@.tld"));
    }

    /**
     * @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1;
     *   @ensures \result = false;
     * }
     */
    @Test
    @DisplayName("mailAddress contains tld is valid")
    void testIsValidMailAddressWithThreeLengthTldReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }

    @Test
    @DisplayName("mailAddress tld.length = 1  is valid")
    void testIsValidMailAddressWithOneLengthTldReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.t"));
    }

    @Test
    @DisplayName("mailAddress !contains tld is invalid")
    void testIsValidMailAddressWithoutTldReturnsFalse() {
        Assertions.assertFalse(MailTools.validateMailAddress("local@subdomain"));
    }

    /**
     * @subcontract valid email {
     *   @requires no other precondition
     *   @ensures \result = true;
     * }
     */
    @Test
    @DisplayName("mailAddress is valid")
    void testIsValidMailAddressWithValidEmailReturnsTrue() {
        Assertions.assertTrue(MailTools.validateMailAddress("local@subdomain.tld"));
    }
}
