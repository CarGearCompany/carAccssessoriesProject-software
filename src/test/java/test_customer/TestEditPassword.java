package test_customer;

import controllers.CustomerController;
import controllers.LoginController;
import exceptions.*;
import io.cucumber.java.en.*;
import models.CarGear;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEditPassword {
    private String password;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws InvalidPhoneNumberException, UserAlreadyExistsException, InvalidEmailFormatException, WeakPasswordException, ItemNotFoundException {
        CarGear.initData();
    }

    @Given("the user is already logged in with {string} and {string}")
    public void theUserIsAlreadyLoggedInWithAnd(String string, String string2) throws UserNotFoundException, InvalidEmailFormatException {
        LoginController.login(string,string2);
    }

    @When("the customer enter the password {string}")
    public void theCustomerEnterThePassword(String string) {
        this.password=string;
    }
    @Then("the password will edit successfully and nothing will be thrown")
    public void thePasswordWillEditSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(()-> CustomerController.editPassword(password));
    }
    @Then("the password failed to edit and nothing weak password exception will be thrown")
    public void thePasswordFailedToEditAndNothingWeakPasswordExceptionWillBeThrown() {
        assertThrows(WeakPasswordException.class, () -> CustomerController.editPassword(password));
    }
}
