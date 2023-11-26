package views;

import controllers.SignUpController;
import enums.Gender;
import enums.UserType;
import exceptions.*;
import models.ContactInfo;
import models.Location;
import models.Name;
import scanners.CustomizedScanners;

import java.util.Scanner;
import java.util.logging.Logger;
public class SignUpView {

    private static final Logger logger = Logger.getLogger(SignUpView.class.getName());
    private static final String EMAIL = "Email";
    private SignUpView(){

    }

    public static void signUp(){
        // his name
    String firstName = CustomizedScanners.scanNonEmptyString("First Name", new Scanner(System.in));
    String lastName = CustomizedScanners.scanNonEmptyString("Last Name", new Scanner(System.in));

    int age = CustomizedScanners.scanInt("Age", new Scanner(System.in));
    Gender gender = CustomizedScanners.scanGender(new Scanner(System.in));

         //his contact info
    String email = CustomizedScanners.scanNonEmptyString(EMAIL,new Scanner(System.in));
    String password = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
    String phoneNumber = CustomizedScanners.scanNonEmptyString("Phone Number", new Scanner(System.in));
    String city = CustomizedScanners.scanNonEmptyString("City",new Scanner(System.in));
    String street = CustomizedScanners.scanNonEmptyString("Street",new Scanner(System.in));


        // his userType
    UserType userType = CustomizedScanners.scanUserType(new Scanner(System.in)) ;

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
            email = CustomizedScanners.scanNonEmptyString(EMAIL,new Scanner(System.in));
        }catch (WeakPasswordException e){
            logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
            password = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
        }catch (InvalidPhoneNumberException e){
            logger.warning("Invalid phoneNumber! Must be 10 digits long");
            phoneNumber = CustomizedScanners.scanNonEmptyString("Phone Number", new Scanner(System.in));
        }catch (UserAlreadyExistsException e){
            logger.warning("Email is already used!");
            email = CustomizedScanners.scanNonEmptyString(EMAIL,new Scanner(System.in));
        }catch (CannotSignUpAsAdminException e){
            logger.warning("Cant SignUp As Admin!");
            userType = CustomizedScanners.scanUserType(new Scanner(System.in));
        }


    }




    }


}
