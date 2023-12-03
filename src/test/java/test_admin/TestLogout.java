package test_admin;

import controllers.LoginController;
import controllers.LogoutController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestLogout {

    @Given("a user is already logged in with {string} and {string}")
    public void aUserIsAlreadyLoggedInWithAnd(String string, String string2) throws UserNotFoundException, InvalidEmailFormatException {
       LoginController.login(string, string2);
    }
    @When("user logged out")
    public void userLoggedOut() {
        LogoutController.logout();
    }
    @Then("the current user will be null")
    public void theCurrentUserWillBeNull() {
        assertNull(CarGear.getCurrentUser());
    }
}
