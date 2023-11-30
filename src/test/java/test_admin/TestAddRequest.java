package test_admin;

import controllers.AdminController;
import exceptions.*;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Customer;
import models.Installer;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class TestAddRequest {
    String installerEmail,customerEmail,date,carModel,category;
    int productId;
    Installer installer;
    Customer customer;
    MyAppExceptions exceptions;
    @When("the admin enter the customer email {string}")
    public void theAdminEnterTheCustomerEmail(String string) throws UserNotFoundException, InvalidEmailFormatException {
        this.customerEmail =string;
        customer = (Customer) CarGear.getUserByEmail(customerEmail);
    }
    @When("the installer email {string}")
    public void theInstallerEmailHalaGmailCom(String string) throws UserNotFoundException, InvalidEmailFormatException {
       this.installerEmail = string;
       installer = (Installer) CarGear.getUserByEmail(installerEmail);
    }
    @When("the date {string}")
    public void theDate(String string) {
        date=string;
    }
    @When("the car model {string}")
    public void theCarModel(String string) {
       carModel=string;
    }
    @When("the category {string}")
    public void theCategory(String string) {
       category=string;
    }
    @When("the product id {int}")
    public void theProductId(Integer int1) {
        productId=int1;
    }
    @Then("the Request will be added successfully and nothing will be thrown")
    public void theRequestWillBeAddedSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(() -> {
            AdminController.addRequest(customerEmail,installerEmail,date,carModel,category,productId);
        });
    }
    @Then("the installer request list size will be {int}")
    public void theInstallerRequestListSizeWillBe(Integer int1) {
        assertEquals(int1,installer.getRequests().size());
    }
    @Then("the customer request list size will be {int}")
    public void theCustomerRequestListSizeWillBe(Integer int1) {
        assertEquals(int1,customer.getRequests().size());
    }
    @Then("the date is installer schedule will be reserved {string}")
    public void theDateIsInstallerScheduleWillBeReserved(String string) throws ItemNotFoundException {
        assertTrue(installer.getScheduleByDate(date).getReserved());
    }







}
