package milestone;

public class MailTools {

    /**
     * @desc Validates if mailAddress is valid. It should be in the form of:
     *       <at least 1 character>@<at least 1 character>.<at least 1 character>
     *
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \result = false;
     * }
     *
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \result = false;
     * }
     *
     * @subcontract no subdomain part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[0].length < 1;
     *   @ensures \result = false;
     * }
     *
     * @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1;
     *   @ensures \result = false;
     * }
     *
     * @subcontract valid email {
     *   @requires no other precondition
     *   @ensures \result = true;
     * }
     *
     */
    public static boolean validateMailAddress(String mailAddress) {
        /**
         * @regex
         * <.+> one or more of any character except newline
         * <@{1}> exactly 1 '@' character
         * <[^\.@\n]+> one or more of any character except '.', '@' or newline
         * <.{1}> exactly one '.' character
         * <[^\.@\n]+> one or more of any character except '.', '@' or newline
         */
        String simpleEmailRegex = ".+@{1}[^\\.@\\n]+\\.{1}[^\\.@\\n]+";
        return mailAddress.matches(simpleEmailRegex);
    }
}