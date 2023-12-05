package test_admin;

import controllers.AdminController;
import exceptions.AdminsCannotBeRemovedException;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.*;
import models.CarGear;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRemoveUser {
    private String email;
    @When("the email of the user to be removed is {string}")
    public void theEmailOfTheUserToBeRemovedIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        this.email = string;
    }
    @Then("this user will be removed")
    public void thisUserWillBeRemoved() throws UserNotFoundException, AdminsCannotBeRemovedException, InvalidEmailFormatException {
        AdminController.removeUser(email);

        assertThrows(UserNotFoundException.class, () -> CarGear.getUserByEmail(email));
    }

    @Then("nothing will be removed and a user not found exception will be thrown")
    public void nothingWillBeRemovedAndAUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> AdminController.removeUser(email));
    }


    @Then("nothing will be removed and an admin cannot be removed exception will be thrown")
    public void nothingWillBeRemovedAndAnAdminCannotBeRemovedExceptionWillBeThrown() {
        assertThrows(AdminsCannotBeRemovedException.class, () -> AdminController.removeUser(email));
    }
    @Then("nothing will be removed and an invalid email format exception  will be thrown")
    public void nothingWillBeRemovedAndAnInvalidEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> AdminController.removeUser(email));
    }


}
