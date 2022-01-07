package milestone;

public class PostalCode {

    /**
     * @desc Formats the input postal code to a uniform output in the form
     * nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital letters.
     * Spaces before and after the input string are trimmed.
     *
     * @subcontract null postalCode {
     *   @requires postalCode == null;
     *   @signals (NullPointerException) postalCode == null;
     * }
     *
     * @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
     *
     * @subcontract invalid postalCode {
     *   @requires no other valid precondition;
     *   @signals (IllegalArgumentException);
     * }
     */
    public static String formatPostalCode(String postalCode) throws IllegalArgumentException, NullPointerException {
        // Check if input is null
        if (postalCode == null) throw new NullPointerException("The postalCode input cannot be empty.");

        String start = postalCode.trim().substring(0, 4).trim();
        String end = postalCode.trim().substring(4).trim().toUpperCase();
        int code = Integer.parseInt(start);

        // Check if valid input is given
        if (code > 999 && code <= 9999
                && end.length() == 2
                && end.charAt(0) >= 'A'
                && end.charAt(0) <= 'Z'
                && end.charAt(1) >= 'A'
                && end.charAt(1) <= 'Z'
        ) {
            return String.format("%s %s", start, end);
        } else {
            throw new IllegalArgumentException("The given argument is not valid.");
        }
    }
}
