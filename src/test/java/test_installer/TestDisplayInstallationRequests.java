package test_installer;

import controllers.CustomerController;
import controllers.LoginController;
import controllers.LogoutController;
import exceptions.*;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Installer;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDisplayInstallationRequests {
    private int resultSize;
    private String carModel;
    private String date;
    private String category;
    private int id;
    private String installerEmail;
    private String customerEmail;
    private String password;
    @When("the installer wants to list all his installation Requests")
    public void theInstallerWantsToListAllHisInstallationRequests() {
        Installer installer = (Installer) CarGear.getCurrentUser();
        resultSize = installer.getRequests().size();
    }

    @Then("the result size of the installation Requests is {int}")
    public void theResultSizeOfTheInstallationRequestsIs(Integer int1) {
        assertEquals(int1,resultSize);
    }

    @When("the car model is {string}")
    public void theCarModelIs(String string) {
        carModel = string;
    }
    @When("the date is valid date {string}")
    public void theDateIsValidDate(String string) {
        date = string;
    }
    @When("the category is {string}")
    public void theCategoryIs(String string) {
        category = string;
    }
    @When("the product id is {int}")
    public void theProductIdIs(Integer int1) {
        id = int1;
    }



    @When("the installer with {string} gets a request from the customer who logged with {string} and {string}")
    public void theInstallerWithGetsARequestFromTheCustomerWhoLoggedWithAnd(String string, String string2, String string3) throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        installerEmail =string;
        customerEmail = string2;
        password = string3;
    }




    @Then("the result size of the installation Requests must be {int}")
    public void theResultSizeOfTheInstallationRequestsMustBe(Integer int1) throws UserNotFoundException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        LogoutController.logout();
        LoginController.login(customerEmail,password);
        CustomerController.requestService(installerEmail,carModel,date,category,id);
        LogoutController.logout();
        LoginController.login(installerEmail,"Hala@123");
        Installer installer = (Installer) CarGear.getCurrentUser();
        resultSize = installer.getRequests().size();

        assertEquals(int1,resultSize);
    }

}
