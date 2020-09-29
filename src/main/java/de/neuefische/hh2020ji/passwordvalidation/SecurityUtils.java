package de.neuefische.hh2020ji.passwordvalidation;

public class SecurityUtils {

    private static final int MINIMUM_LENGTH = 10;
    public static boolean checkPasswordLength(String password){
        return password.length() >= MINIMUM_LENGTH;
    }

    private static final String DIGITS = "0123456789";
    public static boolean containsNumbers(String password){
        for(int i=0; i < password.length(); i++) {
            String character = ""+password.charAt(i);
            if(DIGITS.contains(character)){
                return true;
            }
        }
        return false;
    }

    public static boolean containsLowercaseLetters(String password) {
        String uppercasePassword = password.toUpperCase();
        return !password.equals(uppercasePassword);
    }

    public static boolean containsSpecialCharacters(String password) {
        String specialCharacters = "(.*[@,#,%,+,\\-,\\[].*$)";
        return password.matches(specialCharacters);
    }

    public static boolean isValidPassword(String password){
        return containsNumbers(password)
                && containsLowercaseLetters(password)
                && containsSpecialCharacters(password)
                && checkPasswordLength(password);
    }

    public static boolean isValidPasswordList(String[] passwordList){
        for (int i = 0; i < passwordList.length; i++) {
            String password = passwordList[i];
            boolean isValid = isValidPassword(password);
            if(!isValid){
                return false;
            }
        }
        return true;
    }

}
