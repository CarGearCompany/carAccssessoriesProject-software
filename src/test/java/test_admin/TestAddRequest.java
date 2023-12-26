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

    @When("the admin enters invalid customer email {string}")
    public void theAdminEntersInvalidCustomerEmail(String string) {
        customerEmail = string;
    }
    @Then("the Request will not be added successfully and user not found exception will be thrown")
    public void theRequestWillNotBeAddedSuccessfullyAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,() -> {
            CarGear.getUserByEmail(customerEmail);
        });
    }

    @When("the admin enters invalid installer email {string}")
    public void theAdminEntersInvalidInstallerEmail(String string) {
        installerEmail = string;
    }
    @Then("the Request will not be added successfully because of the wrong installer email and user not found exception will be thrown")
    public void theRequestWillNotBeAddedSuccessfullyBecauseOfTheWrongInstallerEmailAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,() -> {
            CarGear.getUserByEmail(installerEmail);
        });
    }


    @When("the admin enters already reserved date {string}")
    public void theAdminEntersAlreadyReservedDate(String string) {
       date = string;
    }
    @Then("the Request will not be added successfully and already reserved date exception will be thrown")
    public void theRequestWillNotBeAddedSuccessfullyAndAlreadyReservedDateExceptionWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        CarGear.setCurrentUser(CarGear.getUserByEmail("jana@gmail.com"));
        CustomerController.requestService(mock(EmailService.class),"hala@gmail.com","bmw",date,"interior",0);

        assertThrows(AlreadyReservedDateException.class,() -> {
            AdminController.addRequest("jana@gmail.com","hala@gmail.com",date,carModel,"interior",0);
        });
    }
    @When("the admin enters wrong category name {string}")
    public void theAdminEntersWrongCategoryName(String string) {
        category = string;
    }
    @Then("the Request will not be added successfully and category noy found exception will be thrown")
    public void theRequestWillNotBeAddedSuccessfullyAndCategoryNoyFoundExceptionWillBeThrown() {
        assertThrows(CategoryNotFoundException.class,() -> {
            CarGear.getCategoryByName(category);
        });
    }

    @When("the admin enters correct category name {string} and wrong product id {int}")
    public void theAdminEntersCorrectCategoryNameAndWrongProductId(String string, Integer int1) {
        category = string;
        productId = int1;
    }
    @Then("the Request will not be added successfully and product not found exception exception will be thrown")
    public void theRequestWillNotBeAddedSuccessfullyAndProductNotFoundExceptionExceptionWillBeThrown() {
        assertThrows(ProductNotFoundException.class,() -> {
            CarGear.getProductById(CarGear.getCategoryByName(category),productId);
        });
    }











}
