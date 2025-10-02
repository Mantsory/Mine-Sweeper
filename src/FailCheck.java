public class FailCheck {
    public static boolean isInt(String toCheck ,int subStart ,int subEnd) {
        try {
            Integer.parseInt(toCheck.substring(subStart, subEnd));
            return true;
        } catch (NumberFormatException e) {
            return false; //not an integer
        }
    }//end of isInt
}//end of FailCheck