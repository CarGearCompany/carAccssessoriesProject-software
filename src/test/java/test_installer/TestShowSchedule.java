package test_installer;

import controllers.InstallerController;
import exceptions.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestShowSchedule {

    private String email;
    private String password;


    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws  InvalidPhoneNumberException, UserAlreadyExistsException, InvalidEmailFormatException, WeakPasswordException{
        CarGear.initData();
    }
    @When("email is {string}")
    public void emailIs(String string) {
        this.email = string;
    }
    @When("password is {string}")
    public void passwordIs(String string) {
        this.password = string;
    }
    @Then("the installer's schedule will be printed successfully")
    public void theInstallerSScheduleWillBePrintedSuccessfully() {
        assertDoesNotThrow(()->{
            InstallerController.showSchedule(email);
        });
    }





















}
