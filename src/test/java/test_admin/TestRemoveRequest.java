package test_admin;

import controllers.AdminController;
import controllers.CustomerController;
import controllers.LoginController;
import exceptions.*;
import helpers.EmailService;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Customer;
import models.Installer;

import javax.mail.MessagingException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TestRemoveRequest {
    String installerEmail;
    String date;

    Installer installer;
    Customer customer;
    String email;

    @When("the admin enter the installer email {string}")
    public void theAdminEnterTheInstallerEmail(String string) {
        installerEmail = string;
    }
    @When("enters the date {string}")
    public void entersTheDate(String string) {
        date = string;
    }
    @Then("the Request will be removed successfully and nothing will be thrown")
    public void theRequestWillBeRemovedSuccessfullyAndNothingWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        LoginController.login("jana@gmail.com","Jana@123");
        CustomerController.requestService(mock(EmailService.class),installerEmail,"bmw",date,"interior",0);

        assertDoesNotThrow(() -> {
            AdminController.removeRequest(installerEmail,date);
        });

    }
    @Then("the installer request list size will equals {int}")
    public void theInstallerRequestListSizeWillEquals(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertEquals(int1,installer.getRequests().size());
    }
    @Then("the customer request list size will equals {int}")
    public void theCustomerRequestListSizeWillEquals(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        customer = (Customer) CarGear.getUserByEmail("jana@gmail.com");
        assertEquals(int1,customer.getRequests().size());
    }
    @Then("the date is installer schedule will be not reserved {string}")
    public void theDateIsInstallerScheduleWillBeNotReserved(String string) throws ItemNotFoundException {
        assertFalse(installer.getScheduleByDate(date).getReserved());
    }

    @Then("the request will not be removed and item not found exception will be thrown")
    public void theRequestWillNotBeRemovedAndItemNotFoundExceptionWillBeThrown() {
        assertThrows(ItemNotFoundException.class,() -> {
            AdminController.removeRequest(installerEmail,date);
        });
    }
    @When("the admin enters a wrong installer or customer email {string}")
    public void theAdminEntersAWrongInstallerOrCustomerEmail(String string) {
       email = string;
    }
    @Then("the request will not be removed and User not found exception will be thrown")
    public void theRequestWillNotBeRemovedAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,() -> {
            CarGear.getUserByEmail(email);
        });
    }
    @When("the admin enters a wrong installer or customer email format {string}")
    public void theAdminEntersAWrongInstallerOrCustomerEmailFormat(String string) {
        email = string;
    }
    @Then("the request will not be removed and invalid email format exception will be thrown")
    public void theRequestWillNotBeRemovedAndInvalidEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class,() -> {
            AdminController.removeRequest(email,date);
        });
    }


}
