package apis;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.qaaacademy.restassured.apis.ProductAPI;
import pl.qaaacademy.restassured.environments.Environment;
import pl.qaaacademy.restassured.environments.EnvironmentManager;
import pl.qaaacademy.restassured.models.Product;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BasicProductVerificationAPITest {

    private ProductAPI productAPI;

    @BeforeClass
    public void SetUp() {
        String env = "dev";
        Environment currentEnv = EnvironmentManager.getEnvironment(env);
        productAPI = ProductAPI.get(currentEnv);
    }

    @Test
    public void shouldAddNewProduct() {
        String newDescription = "Chocolate cake";
        int newManufacturer = 1;
        float newPrice = 100.5f;
        String newId = "";
        Product product = new Product(newDescription, newId, newManufacturer, newPrice);
        Product newProduct = productAPI.addNewProductToItemListAndExtractId(product);
        Assert.assertEquals(newProduct.getDescription(), newDescription);
    }

    @Test
    public void shouldUpdatePriceForProductWithID2() {
        float newPrice = 100f;
        String productId = "2";
        Product product = productAPI.updatePriceForProduct(newPrice, productId);
        assertThat(product.getPrice(), equalTo(newPrice));
    }

}

