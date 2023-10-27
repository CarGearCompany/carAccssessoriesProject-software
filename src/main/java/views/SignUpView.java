package views;

import controllers.SignUp;
import exceptions.UserNotFoundException;
import models.CarGear;
import models.Name;
import scanners.CustomScanner;

import java.util.Scanner;
import java.util.logging.Logger;
public class SignUpView {

    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private SignUpView(){

    }

    public static void signUp(){
    String firstName = CustomScanner.scanNonEmptyString("First Name", new Scanner(System.in));
    String lastName = CustomScanner.scanNonEmptyString("Last Name", new Scanner(System.in));
    Name fullName = new Name(firstName,lastName);
    int age = CustomScanner.scanInt("Age", new Scanner(System.in));


    }


}
