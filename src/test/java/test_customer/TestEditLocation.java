package test_customer;

import controllers.CustomerController;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestEditLocation {
    String city , street;
    @When("the customer enter the Location {string} and {string}")
    public void theCustomerEnterTheLocationAnd(String string, String string2) {
        this.city=string;
        this.street=string2;
    }
    @Then("the Location will edit successfully and nothing will be thrown")
    public void theLocationWillEditSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(()->{
            CustomerController.editLocation(city,street);
        });
    }

}
