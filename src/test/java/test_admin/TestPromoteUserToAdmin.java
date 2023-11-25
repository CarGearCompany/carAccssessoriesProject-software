package test_admin;

import controllers.AdminController;
import exceptions.AdminsCannotBePromotedException;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPromoteUserToAdmin {
    private String email;
    @When("the admin enters the {string} of the user")
    public void theAdminEntersTheOfTheUser(String string) {
        this.email=string;
    }
    @Then("the promote will pass and nothing will be thrwon")
    public void thePromoteWillPassAndNothingWillBeThrwon() {
        assertDoesNotThrow(()->{
            AdminController.promoteUser(email);
        });
    }
    @Then("the promote willn't pass due to user not found exception")
    public void thePromoteWillnTPassDueToUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> AdminController.promoteUser(email));
    }

    @Then("the promote willn't pass due to admin cannot be promoted exception")
    public void thePromoteWillnTPassDueToAdminCannotBePromotedException() {
        assertThrows(AdminsCannotBePromotedException.class, () -> AdminController.promoteUser(email));
    }

    @Then("the promote willn't pass due to invalid email format exception")
    public void thePromoteWillnTPassDueToInvalidEmailFormatException() {
        assertThrows(InvalidEmailFormatException.class, () -> AdminController.promoteUser(email));
    }
}
