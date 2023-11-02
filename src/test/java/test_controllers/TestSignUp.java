package test_controllers;

import controllers.LoginController;
import controllers.SignUpController;
import enums.Gender;
import enums.UserType;
import exceptions.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.ContactInfo;
import models.Location;
import models.Name;
import scanners.CustomScanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static scanners.CustomScanner.stringToGender;
import static scanners.CustomScanner.stringToUserType;

public class TestSignUp {
    private Name name;

    private int age;
    private Gender gender;
    private String password;
    private UserType userType;
    private ContactInfo contactInfo ;




    @When("newName is {string} {string}")
    public void newNameIs(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);

    }
    @When("newContactInfo is {string} {string} {string} {string}")
    public void newContactInfoIs(String email, String phoneNumber, String city, String street) throws InvalidPhoneNumberException, UserAlreadyExistsException, InvalidEmailFormatException {
        this.contactInfo = new ContactInfo(email,phoneNumber,new Location(city,street));

    }
    @When("age is {int}")
    public void ageIs(Integer age) {
        this.age = age;


    }
    @When("Gender is {string}")
    public void genderIs(String gender) {
        this.gender = stringToGender(gender);

    }
    @When("newPassword is {string}")
    public void newPasswordIs(String password) {
        this.password = password;
    }
    @When("UserType is {string}")
    public void userTypeIs(String userType) {
        this.userType = stringToUserType(userType);

    }
    @Then("User will sign up successfully")
    public void userWillSignUpSuccessfully() throws CannotSignUpAsAdminException, UserAlreadyExistsException, WeakPasswordException {
        assertDoesNotThrow(() -> {
            SignUpController.signUp(name, contactInfo, age, gender, password, userType);
        });


    }

    @Then("User will fail to sign up and user already exists exception will be thrown")
    public void userWillFailToSignUpAndUserAlreadyExistsExceptionWillBeThrown() {
        assertThrows(UserAlreadyExistsException.class, () -> {



            SignUpController.signUp(name, contactInfo, age, gender, password, userType);




        });

    }

    @Then("User will fail to sign up and weak password exception will be thrown")
    public void userWillFailToSignUpAndWeakPasswordExceptionWillBeThrown() {
        assertThrows(WeakPasswordException.class, () ->  SignUpController.signUp(name, contactInfo, age, gender, password, userType));
    }

    @Then("User will fail to sign up and invalid email format exception will be thrown")
    public void userWillFailToSignUpAndInvalidEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> {


            SignUpController.signUp(name, contactInfo, age, gender, password, userType);


        });
    }

    @Then("User will fail to sign up and invalid phone number exception will be thrown")
    public void userWillFailToSignUpAndInvalidPhoneNumberExceptionWillBeThrown() {
        assertThrows(InvalidPhoneNumberException.class, () ->  {

            SignUpController.signUp(name, contactInfo, age, gender, password, userType);




        });
    }

    @Then("User will fail to sign up and cannot sign up as admin exception will be thrown")
    public void userWillFailToSignUpAndCannotSignUpAsAdminExceptionWillBeThrown() {
        assertThrows(CannotSignUpAsAdminException.class, () ->  SignUpController.signUp(name, contactInfo, age, gender, password, userType));
    }


}
