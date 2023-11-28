package test_customer;

import io.cucumber.java.en.*;
import models.CarGear;
import models.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListAllProducts {
    Customer customer;
    private int resultSize;
    @When("the customer wants to list all products")
    public void theCustomerWantsToListAllProducts() {
         customer = (Customer) CarGear.getCurrentUser();
    }
    @Then("the result size of the products will be {int}")
    public void theResultSizeOfTheProductsWillBe(Integer int1) {
        resultSize = CarGear.getAllProducts().size();
        assertEquals(int1,resultSize);
    }
}
