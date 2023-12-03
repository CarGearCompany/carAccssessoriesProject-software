package test_customer;

import controllers.CustomerController;
import exceptions.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Customer;
import models.Product;
import models.User;

import javax.mail.MessagingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPurchaseProducts {
    private String category;
    private int id;
    private String confirm;
    private Customer customer;
    private int resultSize;
    private int quantity;
    private int newQuantity;

    @When("the customer enter category {string}")
    public void theCustomerEnterCategory(String string) {
        this.category = string;
    }
    @When("enters id {int}")
    public void entersId(Integer int1) {
        this.id = int1;
    }
    @When("enters quantity {int}")
    public void entersQuantity(Integer int1) {
        this.quantity = int1;
    }
    @When("confirms with {string}")
    public void confirmsWith(String string) {
        this.confirm = string;
    }
    @Then("the transaction is done succssfully and the pruchasedProducts list size will be {int} and quantity of that product will be {int}")
    public void theTransactionIsDoneSuccssfullyAndThePruchasedProductsListSizeWillBeAndQuantityOfThatProductWillBe(Integer int1, Integer int2) throws PurchaseNotConfirmedException, OutOfStockException, MessagingException, CategoryNotFoundException, ProductNotFoundException, NotEnoughItemsAvailableException {
        customer = (Customer) CarGear.getCurrentUser();
        newQuantity = CustomerController.purchaseProduct(category,id, customer,confirm,quantity);
        List<Product> purchasedProducts = (customer).getPurchasedProducts();
        resultSize = purchasedProducts.size();
        assertEquals(int1,resultSize);
        assertEquals(int2,newQuantity);
    }

    @Then("the transaction fails and product not found exception will be thrown")
    public void theTransactionFailsAndProductNotFoundExceptionWillBeThrown() {
        customer = (Customer) CarGear.getCurrentUser();
        assertThrows(ProductNotFoundException.class, () -> CustomerController.purchaseProduct(category,id,customer,confirm,quantity));
    }

    @Then("the transaction fails and category not found exception will be thrown")
    public void theTransactionFailsAndCategoryNotFoundExceptionWillBeThrown() {
        customer = (Customer) CarGear.getCurrentUser();
        assertThrows(CategoryNotFoundException.class, () -> CustomerController.purchaseProduct(category,id, customer,confirm,quantity));
    }

    @Then("the transaction is not completed and purchase not confirmed exception will be thrown")
    public void theTransactionIsNotCompletedAndPurchaseNotConfirmedExceptionWillBeThrown() {
        customer = (Customer) CarGear.getCurrentUser();
        assertThrows(PurchaseNotConfirmedException.class, () -> CustomerController.purchaseProduct(category,id,customer,confirm,quantity));
    }

    @Then("the transaction is not done and out of stock exception will be thrown")
    public void theTransactionIsNotDoneAndOutOfStockExceptionWillBeThrown() {
        customer = (Customer) CarGear.getCurrentUser();
        assertThrows(OutOfStockException.class, () -> CustomerController.purchaseProduct(category,id,customer,confirm,quantity));
    }

    @Then("the transaction is not done and not enough items available exception will be thrown")
    public void theTransactionIsNotDoneAndNotEnoughItemsAvailableExceptionWillBeThrown() {
        customer = (Customer) CarGear.getCurrentUser();
        assertThrows(NotEnoughItemsAvailableException.class, () -> CustomerController.purchaseProduct(category,id,customer,confirm,quantity));

    }

}
