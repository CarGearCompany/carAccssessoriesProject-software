package test_admin;

import controllers.AdminController;
import exceptions.*;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Category;
import models.Customer;
import models.Installer;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

public class TestEditRequest {
    String installerEmail;
    String date;
    String editType;
    String value;
    String categoryName;
    int productId;
    Installer installer;
    Customer customer;
    @When("the installer email is {string}")
    public void theInstallerEmailIs(String string) {
        installerEmail = string;
    }
    @Then("the request will not be updated and user not found will be thrown")
    public void theRequestWillNotBeUpdatedAndUserNotFoundWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> CarGear.getUserByEmail(installerEmail));
    }
    @When("the invalid format installer email is {string}")
    public void theInvalidFormatInstallerEmailIs(String string) {
        installerEmail = string;
    }
    @Then("the request will not be updated and invalid email format will be thrown")
    public void theRequestWillNotBeUpdatedAndInvalidEmailFormatWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> CarGear.getUserByEmail(installerEmail));
    }
    @When("the date is {string}")
    public void theDateIs(String string) {
        date = string;
    }
    @Then("the request will not be updated and item not found will be thrown")
    public void theRequestWillNotBeUpdatedAndItemNotFoundWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException {
        Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertThrows(ItemNotFoundException.class, () -> installer.getScheduleByDate(date));
    }

    @When("the edit field is {string}")
    public void theEditFieldIs(String string) {
        editType = string;
    }
    @When("the new value is {string}")
    public void theNewValueIs(String string) {
        value = string;
    }
    @Then("the request will not be updated and already reserved date will be thrown")
    public void theRequestWillNotBeUpdatedAndAlreadyReservedDateWillBeThrown() throws UserNotFoundException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        AdminController.addRequest("jana@gmail.com",installerEmail,"8/2/2024","bmw","interior",0);
        AdminController.addRequest("mahmoud.shouli.yes@gmail.com",installerEmail,"15/2/2024","bmw","interior",0);
        assertThrows(AlreadyReservedDateException.class, () -> AdminController.editRequest(installerEmail,date,editType,value,null));
    }

    @When("the category name is {string}")
    public void theCategoryNameIs(String string) {
        categoryName = string;
    }
    @Then("the request will not be updated and category not found will be thrown")
    public void theRequestWillNotBeUpdatedAndCategoryNotFoundWillBeThrown() {
        assertThrows(CategoryNotFoundException.class, () -> CarGear.getCategoryByName(categoryName));
    }

    @When("product id is {int}")
    public void productIdIs(Integer int1) {
        productId = int1;
    }
    @Then("the request will not be updated and product not found will be thrown")
    public void theRequestWillNotBeUpdatedAndProductNotFoundWillBeThrown() throws CategoryNotFoundException {
        Category category = CarGear.getCategoryByName(categoryName);
        assertThrows(ProductNotFoundException.class, () -> CarGear.getProductById(category,productId));
    }

    @Then("the request will be edited successfully")
    public void theRequestWillBeEditedSuccessfully() throws UserNotFoundException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        AdminController.addRequest("jana@gmail.com",installerEmail,date,"bmw","interior",0);
        assertDoesNotThrow(()-> AdminController.editRequest(installerEmail,date,editType,value,null));
    }
    @Then("the request list size of the old installer will be {int}")
    public void theRequestListSizeOfTheOldInstallerWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertEquals(int1,installer.getRequests().size());
    }
    @Then("the request list size of the new installer will be {int}")
    public void theRequestListSizeOfTheNewInstallerWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        installer = (Installer) CarGear.getUserByEmail(value);
        assertEquals(int1,installer.getRequests().size());
    }

    @Then("the request list size of the old customer will be {int}")
    public void theRequestListSizeOfTheOldCustomerWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        customer = (Customer) CarGear.getUserByEmail("jana@gmail.com");
        assertEquals(int1,customer.getRequests().size());
    }
    @Then("the request list size of the new customer will be {int}")
    public void theRequestListSizeOfTheNewCustomerWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException {
        customer = (Customer) CarGear.getUserByEmail(value);
        assertEquals(int1,customer.getRequests().size());
    }
    @Then("the old date can be booked again")
    public void theOldDateCanBeBookedAgain() throws UserNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertFalse(installer.getScheduleByDate(date).getReserved());
    }
    @Then("the new date is reserved")
    public void theNewDateIsReserved() throws UserNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertTrue(installer.getScheduleByDate(value).getReserved());
    }

    @Then("a new date is added to the installer schedule and the size of it will be {int}")
    public void aNewDateIsAddedToTheInstallerScheduleAndTheSizeOfItWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        AdminController.addRequest("jana@gmail.com",installerEmail,date,"bmw","interior",0);
        installer = (Installer) CarGear.getUserByEmail(installerEmail);
        assertEquals(int1,installer.getSchedules().size());
    }

    @Then("the request will be edited successfully and nothing will be thrown")
    public void theRequestWillBeEditedSuccessfullyAndNothingWillBeThrown() throws UserNotFoundException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        AdminController.addRequest("jana@gmail.com",installerEmail,date,"bmw","interior",0);
        assertDoesNotThrow(()-> AdminController.editRequest(installerEmail,date,editType,value,categoryName));
    }

    @Then("the request will not be edited and nothing will be thrown")
    public void theRequestWillNotBeEditedAndNothingWillBeThrown() throws UserNotFoundException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        AdminController.addRequest("jana@gmail.com",installerEmail,date,"bmw","interior",0);
        assertDoesNotThrow(()-> AdminController.editRequest(installerEmail,date,editType,value,categoryName));
    }



}
