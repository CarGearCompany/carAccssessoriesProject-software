package test_admin;

import controllers.AdminController;
import controllers.CustomerController;
import controllers.LoginController;
import exceptions.*;
import helpers.EmailService;
import io.cucumber.java.en.*;
import models.CarGear;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TestSearchForRequest {
    private String searchField;
    private String value;
    private int resultSize;

    @When("the search type is {string}")
    public void theSearchTypeIs(String string) {
        searchField = string;
    }
    @When("the value is  {string}")
    public void theValueIs(String string) {
        value = string;
    }
    @Then("products are found and number of results must be {int}")
    public void productsAreFoundAndNumberOfResultsMustBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        LoginController.login("jana@gmail.com","Jana@123");
        CarGear.setCurrentUser(CarGear.getUserByEmail("jana@gmail.com"));
        CustomerController.requestService(mock(EmailService.class),"hala@gmail.com","bmw","8/2/2024","interior",0);

        resultSize = AdminController.searchForRequests(searchField,value).size();
        assertEquals(int1,resultSize);

    }

}
