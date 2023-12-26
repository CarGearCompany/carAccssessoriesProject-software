package test_customer;

import controllers.CustomerController;
import controllers.LoginController;
import exceptions.*;
import helpers.EmailService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import javax.mail.MessagingException;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TestRequestInstallationService {

    private Installer installer;
    private Customer customer;
    private String email,model,date,category;

    private int id,resultSize1,resultSize2;




    @When("the customer wants to request a service")
    public void theCustomerWantsToRequestAService() {
        customer = (Customer) CarGear.getCurrentUser();
    }
    @When("enters installerEmail {string}")
    public void entersInstallerEmail(String string)  {
        this.email = string;

    }
    @When("enters CarModel {string}")
    public void entersCarModel(String string) {
        this.model = string;
    }
    @When("enters available date {string}")
    public void entersAvailableDate(String string) {
        this.date = string;
    }
    @When("enters category {string}")
    public void entersCategory(String string) {
        this.category = string;
    }
    @When("enters the product id {int}")
    public void entersTheProductId(Integer int1) {
        this.id = int1;
    }
    @Then("the service will be requested and the request will be added to the installer's and customer's request list and their size will be {int}")
    public void theServiceWillBeRequestedAndTheRequestWillBeAddedToTheInstallerSAndCustomerSRequestListAndTheirSizeWillBe(Integer int1) throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        installer = (Installer) CarGear.getUserByEmail(email) ;
        CustomerController.requestService(mock(EmailService.class),email,model,date,category,id);
        resultSize1 = customer.getRequests().size();
        resultSize2 = installer.getRequests().size();
        assertEquals(int1,resultSize1);
        assertEquals(int1,resultSize2);


    }

    @Then("the request will fail and the category not found exception  will be thrown")
    public void theRequestWillFailAndTheCategoryNotFoundExceptionWillBeThrown()  {

        assertThrows(CategoryNotFoundException.class, () -> CustomerController.requestService(mock(EmailService.class),email,model,date,category,id));
    }




    @Then("the request will fail and the product not found exception  will be thrown")
    public void theRequestWillFailAndTheProductNotFoundExceptionWillBeThrown()  {

        assertThrows(ProductNotFoundException.class, () -> CustomerController.requestService(mock(EmailService.class),email,model,date,category,id));
    }




    @Then("the request will fail and the user not found exception  will be thrown")
    public void theRequestWillFailAndTheUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,()-> CarGear.getUserByEmail(email));

        assertThrows(UserNotFoundException.class, () -> CustomerController.requestService(mock(EmailService.class),email,model,date,category,id));
    }




    @Then("the request will fail and the invalid email format exception  will be thrown")
    public void theRequestWillFailAndTheInvalidEmailFormatExceptionWillBeThrown() {

        assertThrows(InvalidEmailFormatException.class, () -> CustomerController.requestService(mock(EmailService.class),email,model,date,category,id));
    }




    @Then("the request will fail and the date already booked exception  will be thrown")
    public void theRequestWillFailAndTheDateAlreadyBookedExceptionWillBeThrown() throws UserNotFoundException, InvalidEmailFormatException, MessagingException, AlreadyReservedDateException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException {
        LoginController.login("jana@gmail.com","Jana@123");
        CarGear.setCurrentUser(CarGear.getUserByEmail("jana@gmail.com"));
        CustomerController.requestService(mock(EmailService.class),email,model,date,category, id);

        assertThrows(AlreadyReservedDateException.class, () -> CustomerController.requestService(mock(EmailService.class),email,model,date,category,id));
    }


}
