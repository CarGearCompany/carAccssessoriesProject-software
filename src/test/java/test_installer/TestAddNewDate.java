package test_installer;

import controllers.InstallerController;
import exceptions.AlreadyReservedDateException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddNewDate {
 private String date;
    @When("the Installer enter the new Date {string}")
    public void theInstallerEnterTheNewDate(String string) {
        date=string;
    }
    @Then("the date will added successfully and nothing will be thrown")
    public void theDateWillAddedSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(()-> InstallerController.addDateToSchedule(date));
    }
    @Then("the date willNot be added and AlreadyReservedDate Exception will be thrown")
    public void theDateWillNotBeAddedAndAlreadyReservedDateExceptionWillBeThrown() {
        assertThrows(AlreadyReservedDateException.class, () -> InstallerController.addDateToSchedule(date));
    }

}
