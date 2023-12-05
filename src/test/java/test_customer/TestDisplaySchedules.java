package test_customer;

import controllers.CustomerController;
import controllers.InstallerController;
import controllers.LoginController;
import controllers.LogoutController;
import exceptions.AlreadyReservedDateException;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import models.CarGear;
import models.Customer;
import models.Installer;

public class TestDisplaySchedules {
    private int resultSize;

    private String date;

    private String installerEmail;
    private String customerEmail;
    private String password;
    @When("the customer wants to see the schedules")
    public void theCustomerWantsToSeeTheSchedules() throws WeakPasswordException {
       Customer customer = (Customer) CarGear.getCurrentUser();
        resultSize = CarGear.getSchedules().size();
    }
    @Then("the result size of the schedules is {int}")
    public void theResultSizeOfTheSchedulesIs(Integer int1) {
        assertEquals(int1,resultSize);
    }




    @When("the customer with {string} sees an update after the installer is who logged with {string} and {string} adds a new date")
    public void theCustomerWithSeesAnUpdateAfterTheInstallerIsWhoLoggedWithAndAddsANewDate(String string, String string2, String string3) {
        this.customerEmail =string;
        this.installerEmail = string2;
        this.password = string3;
    }
    @When("the date is valid date {string}")
    public void theDateIsValidDate(String string) {
        this.date = string;
    }
    @Then("the result size of the schedules must be {int}")
    public void theResultSizeOfTheSchedulesMustBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException, AlreadyReservedDateException, WeakPasswordException {
        LogoutController.logout();
        LoginController.login(installerEmail,password);
        InstallerController.addDateToSchedule(date);
        LogoutController.logout();
        LoginController.login(customerEmail,"Saleh@123");
        resultSize = CarGear.getSchedules().size();

        assertEquals(int1,resultSize);
    }


}
