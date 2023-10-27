package helpers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class PasswordChecker {
    private PasswordChecker() {
    }

    public static boolean isStrongPassword(String password){
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();




    }
}
