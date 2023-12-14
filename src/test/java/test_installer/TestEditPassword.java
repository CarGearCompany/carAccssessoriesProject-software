package test_installer;

import controllers.CustomerController;
import controllers.InstallerController;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEditPassword {
    private String newPass;
    @When("the Installer enter the password {string}")
    public void theInstallerEnterThePassword(String string) {
        newPass = string;
    }
    @Then("the password will edit successfully and nothing will be thrown")
    public void thePasswordWillEditSuccessfullyAndNothingWillBeThrown() throws WeakPasswordException {
        assertDoesNotThrow(()-> InstallerController.editPassword(newPass));
    }
    @Then("the password failed to edit and nothing weak password exception will be thrown")
    public void thePasswordFailedToEditAndNothingWeakPasswordExceptionWillBeThrown() {
        assertThrows(WeakPasswordException.class, () -> InstallerController.editPassword(newPass));
    }


}
