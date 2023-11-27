package test_customer;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Customer;
import models.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDisplayPurchasedProducts {


    private int resultSize;
    @When("the customer wants to list all his purchased products")
    public void theCustomerWantsToListAllHisPurchasedProducts() {
        Customer customer = (Customer) CarGear.getCurrentUser();
        resultSize = customer.getPurchasedProducts().size();

    }
    @Then("the result size of the products must be {int}")
    public void theResultSizeOfTheProductsMustBe(Integer int1) {
        assertEquals(int1,resultSize);
    }
}
