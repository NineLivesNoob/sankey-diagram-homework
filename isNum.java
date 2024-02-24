public abstract class isNum {
    public static boolean isNum (String number){
        if (number == null) {
            return false;
        }
        int len = number.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

