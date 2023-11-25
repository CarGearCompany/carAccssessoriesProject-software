package views;

import controllers.SignUpController;
import enums.Gender;
import enums.UserType;
import exceptions.*;
import models.ContactInfo;
import models.Location;
import models.Name;
import scanners.SpecifiedScanner;

import java.util.Scanner;
import java.util.logging.Logger;
public class SignUpView {

    private static final Logger logger = Logger.getLogger(SignUpView.class.getName());
    private static final String EMAIL = "Email";
    private SignUpView(){

    }

    public static void signUp(){
        // his name
    String firstName = SpecifiedScanner.scanNonEmptyString("First Name", new Scanner(System.in));
    String lastName = SpecifiedScanner.scanNonEmptyString("Last Name", new Scanner(System.in));

    int age = SpecifiedScanner.scanInt("Age", new Scanner(System.in));
    Gender gender = SpecifiedScanner.scanGender(new Scanner(System.in));

         //his contact info
    String email = SpecifiedScanner.scanNonEmptyString(EMAIL,new Scanner(System.in));
    String password = SpecifiedScanner.scanNonEmptyString("Password", new Scanner(System.in));
    String phoneNumber = SpecifiedScanner.scanNonEmptyString("Phone Number", new Scanner(System.in));
    String city = SpecifiedScanner.scanNonEmptyString("City",new Scanner(System.in));
    String street = SpecifiedScanner.scanNonEmptyString("Street",new Scanner(System.in));


        // his userType
    UserType userType = SpecifiedScanner.scanUserType(new Scanner(System.in)) ;

    while (true){
        try {
            Name fullName = new Name(firstName,lastName);
            Location newLocation = new Location(city,street);
            ContactInfo newContactinfo = new ContactInfo(email,phoneNumber,newLocation);

            SignUpController.signUp(fullName, newContactinfo, age, gender, password, userType);
            logger.info("Sign up successfully!");
            break;
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL,new Scanner(System.in));
        }catch (WeakPasswordException e){
            logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
            password = SpecifiedScanner.scanNonEmptyString("Password", new Scanner(System.in));
        }catch (InvalidPhoneNumberException e){
            logger.warning("Invalid phoneNumber! Must be 10 digits long");
            phoneNumber = SpecifiedScanner.scanNonEmptyString("Phone Number", new Scanner(System.in));
        }catch (UserAlreadyExistsException e){
            logger.warning("Email is already used!");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL,new Scanner(System.in));
        }catch (CannotSignUpAsAdminException e){
            logger.warning("Cant SignUp As Admin!");
            userType = SpecifiedScanner.scanUserType(new Scanner(System.in));
        }


    }




    }


}
