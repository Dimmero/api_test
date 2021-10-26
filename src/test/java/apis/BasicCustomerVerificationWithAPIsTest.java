package apis;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.qaaacademy.restassured.apis.CustomerAPI;
import pl.qaaacademy.restassured.environments.Environment;
import pl.qaaacademy.restassured.environments.EnvironmentManager;
import pl.qaaacademy.restassured.models.Customer;

import java.util.List;

public class BasicCustomerVerificationWithAPIsTest {

    private CustomerAPI customerAPI;

    @BeforeClass
    public void SetUp() {
        String env = "dev";
        Environment currentEnv = EnvironmentManager.getEnvironment(env);
        customerAPI = CustomerAPI.get(currentEnv);
    }

    @Test
    public void shouldGetListOfExistingCustomers() {
        List<Customer> customers = customerAPI.getAllCustomers();
        Assert.assertTrue(customers.size() > 0, "Customer list size is 0, check logs for additional details");
    }

    @Test
    public void shouldUpdateCustomerEmailWithNewOne() {
        String customerId = "3";
        String newEmail = "alex.kowalsky@changed.yes";
        Customer alex = customerAPI.updateEmailAddressForCustomer(customerId, newEmail);
        Assert.assertEquals(newEmail, alex.getPerson().getEmail());
    }

    @Test
    public void checkIfItemWithIdWasAddedToCart() {
        String itemId = "3";
        String quantity = "3";
        String customerId = "4";
        customerAPI.addItemToCartForCustomer(customerId, itemId, quantity);
        Customer dima = customerAPI.getCustomerById(customerId);
        Assert.assertTrue(dima.getShoppingCart().getItems().length > 0);
    }
}
