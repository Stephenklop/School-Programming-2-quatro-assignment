public class PostalCode {

    /**
     * @desc Formats the input postal code to a uniform output in the form
     *       nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital
     *       letters.
     *       Spaces before and after the input string are trimmed.
     * 
     * @subcontract null postalCode {
     * @requires postalCode == null;
     * @signals (NullPointerException) postalCode == null;
     *          }
     * 
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
     * 
     * @subcontract invalid postalCode {
     * @requires no other valid precondition;
     * @signals (IllegalArgumentException);
     *          }
     * 
     */
    public static String formatPostalCode(String postalCode) {

        // Check if input is null
        if (postalCode == null)
            throw new NullPointerException("The postalCode input cannot be empty.");

        // Check if valid input is given
        if (Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
                Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
                postalCode.trim().substring(4).trim().length() == 2 &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(0) >= 'A' &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(1) >= 'A' &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(1) <= 'Z') {
            return postalCode.trim().substring(0, 4) + " " +
                    postalCode.trim().substring(4).trim().toUpperCase();

        } else

        {
            throw new IllegalArgumentException("The given argument is not valid.");
        }
    }
}
