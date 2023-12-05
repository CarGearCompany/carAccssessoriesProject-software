package test_admin;

import controllers.AdminController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Schedule;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddScheduleToInstaller
{
    String date;
    String installerEmail;
    Schedule schedule;
    @When("the admin enter the date {string}")
    public void theAdminEnterTheDate(String string) {
        date = string;
    }
    @When("the admin enter the email {string}")
    public void theAdminEnterTheEmail(String string) {
        installerEmail = string;
    }

    @Then("the schedule will be added successfully")
    public void theScheduleWillBeAddedSuccessfully() throws UserNotFoundException, InvalidEmailFormatException {
        schedule = new Schedule(date,false,installerEmail);
        AdminController.addScheduleToInstaller(installerEmail,schedule);
    }

    @Then("the schedule cant be added successfully and UseNotFound exception will be thrown")
    public void theScheduleCantBeAddedSuccessfullyAndUseNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> CarGear.getUserByEmail(installerEmail));
    }
    @Then("the schedule cant be added successfully and NotValidFormat exception will be thrown")
    public void theScheduleCantBeAddedSuccessfullyAndNotValidFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> CarGear.getUserByEmail(installerEmail));
    }


}
