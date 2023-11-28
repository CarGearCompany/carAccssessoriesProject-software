package test_customer;

import controllers.CustomerController;
import exceptions.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Customer;
import models.Product;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDisplayPurchasedProducts {

    private String category;
    private int id;
    private String confirm;
    private Customer customer;
    private int quantity;
    private int resultSize;
    @When("the customer wants to list all his purchased products")
    public void theCustomerWantsToListAllHisPurchasedProducts() {
        customer = (Customer) CarGear.getCurrentUser();
        resultSize = customer.getPurchasedProducts().size();

    }
    @Then("the result size of the products must be {int}")
    public void theResultSizeOfTheProductsMustBe(Integer int1) {
        assertEquals(int1,resultSize);
    }

    @When("the customer enter  category {string}")
    public void theCustomerEnterCategory(String string) {
        this.category = string;
    }
    @When("enters  id {int}")
    public void entersId(Integer int1) {
        this.id = int1;
    }
    @When("enters  quantity {int}")
    public void entersQuantity(Integer int1) {
        this.quantity = int1;
    }
    @When("confirms  with {string}")
    public void confirmsWith(String string) {
        this.confirm = string;
    }
    @Then("the result size of the products must be updated to {int}")
    public void theResultSizeOfTheProductsMustBeUpdatedTo(Integer int1) throws PurchaseNotConfirmedException, OutOfStockException, MessagingException, NotEnoughItemsAvailableException, CategoryNotFoundException, ProductNotFoundException {
        customer = (Customer) CarGear.getCurrentUser();
        CustomerController.purchaseProduct(category,id,customer,confirm,quantity);
        resultSize = customer.getPurchasedProducts().size();
        assertEquals(int1,resultSize);
    }
}
