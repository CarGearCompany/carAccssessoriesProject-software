package test_authentication;

import controllers.CustomerController;
import controllers.ForgetPasswordController;
import controllers.LoginController;
import exceptions.*;
import helpers.EmailService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.User;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestForgetPassword {

    private String email;
    private User user;
    private String userCode;
    private String emailCode;
    private String newPass;
    private String confirmPass;
    private String newUpdatedPass;


    @When("user enters email  {string}")
    public void userEntersEmail(String string) throws UserNotFoundException, InvalidEmailFormatException {
        this.email = string;


    }
    @When("enters {string} he got from email {string}")
    public void entersHeGotFromEmail(String string, String string2) {
        this.userCode = string;
        this.emailCode = string2;
    }
    @When("enters new password {string}")
    public void entersNewPassword(String string) {
        this.newPass = string;
    }
    @When("confirms the new password {string}")
    public void confirmsTheNewPassword(String string) {
        this.confirmPass = string;
    }


    @Then("the password is reset and equals {string}")
    public void thePasswordIsResetAndEquals(String string) throws NotEqualCodesException, NotEqualPasswordsException, WeakPasswordException, UserNotFoundException, InvalidEmailFormatException {
        user = ForgetPasswordController.getUser(email);
        ForgetPasswordController.areEqual(newPass,confirmPass,1,user);
        assertEquals(string,user.getPassword());
    }


    @Then("user not found exception will be thrown")
    public void userNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> ForgetPasswordController.getUser(email));

    }

    @Then("invalid email format exception will be thrown")
    public void invalidEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> ForgetPasswordController.getUser(email));
    }

    @Then("not equal codes exception will be thrown")
    public void notEqualCodesExceptionWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException {
        user = ForgetPasswordController.getUser(email);
        assertThrows(NotEqualCodesException.class, () -> ForgetPasswordController.areEqual(userCode,emailCode,0,user));
    }


    @Then("not equal passwords exception will be thrown")
    public void notEqualPasswordsExceptionWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException {
        user = ForgetPasswordController.getUser(email);
        assertThrows(NotEqualPasswordsException.class, () -> ForgetPasswordController.areEqual(newPass,confirmPass,1,user));
    }


    @Then("weak password exception will be thrown")
    public void weakPasswordExceptionWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException {
        user = ForgetPasswordController.getUser(email);
        assertThrows(WeakPasswordException.class, () -> ForgetPasswordController.areEqual(newPass,confirmPass,1,user));
    }

    @Then("email verification is sent")
    public void emailVerificationIsSent() {
        assertDoesNotThrow(()->{
            ForgetPasswordController.getCode(mock(EmailService.class),email);
        });
    }
}
