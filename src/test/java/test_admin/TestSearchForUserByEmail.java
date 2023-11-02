package test_admin;

import controllers.AdminController;
import controllers.SignUpController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestSearchForUserByEmail {
    private String name;
    @When("the admin enter email is {string}")
    public void theAdminEnterEmailIs(String string) {
        this.name=string;
    }
    @Then("the object is returned nothing will be thrown")
    public void theObjectIsReturnedNothingWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException {

        assertDoesNotThrow(() -> {
            AdminController.searchForUserByEmail(name);
        });
    }
    @Then("nothing will returned and the user not found exception will be thrown")
    public void nothingWillReturnedAndTheUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,() -> {
            AdminController.searchForUserByEmail(name);
        });
    }

    @Then("nothing will returned and the invalided email format exception will be thrown")
    public void nothingWillReturnedAndTheInvalidedEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class,() -> {
            AdminController.searchForUserByEmail(name);
        });
    }
}
