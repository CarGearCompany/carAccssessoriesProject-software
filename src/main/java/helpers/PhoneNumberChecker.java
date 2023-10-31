package helpers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class PhoneNumberChecker {
    private PhoneNumberChecker() {
    }

    public static boolean  isValidPhoneNumber(String phoneNumber){
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();


    }
}
