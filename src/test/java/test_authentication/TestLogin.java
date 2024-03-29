package test_authentication;

import controllers.LoginController;
import enums.UserType;
import exceptions.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;

import static org.junit.Assert.*;


public class TestLogin {
    private String email;
    private String password;

    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws UserAlreadyExistsException, WeakPasswordException {
        CarGear.initData();
    }
    @When("email is {string}")
    public void emailIs(String email) {
        this.email = email;

    }
    @When("password is {string}")
    public void passwordIs(String password) {
        this.password = password;

    }
    @Then("the user will login successfully")
    public void theUserWillLoginSuccessfully() throws UserNotFoundException, InvalidEmailFormatException {
        assertTrue(LoginController.login(email,password));
        assertNotNull(CarGear.getCurrentUser());

    }

    @Then("the user will fail to login due to not existing email and user not found exception will be thrown")
    public void theUserWillFailToLoginDueToNotExistingEmailAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> LoginController.login(email, password));
    }

    @Then("the user will fail to login due to wrong password")
    public void theUserWillFailToLoginDueToWrongPassword() throws UserNotFoundException, InvalidEmailFormatException {
        assertFalse(LoginController.login(email, password));
    }
    @Then("the current userType will be admin")
    public void theCurrentUserTypeWillBe() throws UserNotFoundException, InvalidEmailFormatException {
        LoginController.login(email,password);
        assertEquals(UserType.ADMIN,LoginController.getCurrentUserType());
    }



}
