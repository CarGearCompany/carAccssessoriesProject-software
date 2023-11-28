package test_installer;

import controllers.CustomerController;
import controllers.LoginController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.*;
import models.CarGear;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestEditLocation {

    String city , street;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws UserAlreadyExistsException, WeakPasswordException {
        CarGear.initData();
    }
    @Given("the user is already logged in with {string} and {string}")
    public void theUserIsAlreadyLoggedInWithAnd(String string, String string2) throws UserNotFoundException, InvalidEmailFormatException {
        LoginController.login(string,string2);
    }
    @When("the Installer enter the Location {string} and {string}")
    public void theInstallerEnterTheLocationAnd(String string, String string2) {
        city=string;
        street=string2;
    }
    @Then("the Location will edit successfully and nothing will be thrown")
    public void theLocationWillEditSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(()->{
            CustomerController.editLocation(city,street);
        });
    }
}
