public class FailCheck {
    public static boolean isInt(String toCheck ,int subStart ,int subEnd) {
        try {
            Integer.parseInt(toCheck.substring(subStart, subEnd));
            return true;
        } catch (NumberFormatException e) {
            return false; //not an integer
        }
    }//end of isInt
    public static boolean isChar(String toCheck ,int charLoc) {
        try {
            toCheck = toCheck.toUpperCase();
            char c = toCheck.charAt(charLoc);
            return c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J';
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }//end of isChar
    public static boolean isText(String checkText, String text) {
        checkText = checkText.toLowerCase();
        text = text.toLowerCase();
        return checkText.contains(text);
    }//end of hasText
}//end of FailCheck