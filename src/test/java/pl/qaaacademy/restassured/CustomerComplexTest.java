package pl.qaaacademy.restassured;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qaaacademy.restassured.models.Customer;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CustomerComplexTest {

    private final String BASE_PATH_CUSTOMERS = "http://localhost:3000/customers";
    private final String SEPARATOR = "/";

    @Test
    public void customerShoppingCartItemListShouldHaveLength0() {
        Customer customer = when().get(BASE_PATH_CUSTOMERS + "/3").then().extract().as(Customer.class);
        Assert.assertEquals(customer.getShoppingCart().getItems().length, 0);
    }

    @Test
    public void customerShoppingCartItemListShouldHaveLength1() {
        Customer customer = when().get(BASE_PATH_CUSTOMERS + "/2").then().extract().as(Customer.class);
        Assert.assertEquals(customer.getShoppingCart().getItems().length, 1);
    }

    @Test
    public void addingItemToTheCartAndCheckCartItemList() {
        Customer customer = given().body("{\n" +
                        "        \"address\": {\n" +
                        "            \"city\": \"Auckland\",\n" +
                        "            \"country\": \"New Zeland\",\n" +
                        "            \"phone\": \"44 33 548 456\",\n" +
                        "            \"street\": \"Samlet St.\",\n" +
                        "            \"streetNumber\": \"1\",\n" +
                        "            \"zipcode\": 612\n" +
                        "        },\n" +
                        "        \"customerStatus\": 0,\n" +
                        "        \"password\": \"1234\",\n" +
                        "        \"person\": {\n" +
                        "            \"email\": \"melinda.stone@customDomain.com\",\n" +
                        "            \"lastname\": \"Dima\",\n" +
                        "            \"phone\": \"33 55 789 123\",\n" +
                        "            \"surname\": \"Stone\"\n" +
                        "        }\n" +
                        "}").contentType(ContentType.JSON)
                .when().post(BASE_PATH_CUSTOMERS).then().extract().as(Customer.class);
        Assert.assertEquals(customer.getPerson().getLastname(), "Dima");
    }

    private void putItemToCart(String customerID, int productID, int productQuantity) {
        String query = BASE_PATH_CUSTOMERS + SEPARATOR + customerID + SEPARATOR + "cart";
        given().queryParam("quantity", productQuantity)
                .queryParam("productId", productID)
                .when().put(query);
    }

    @Test
    public void putItemToCartAndCheck() {
        when().put(BASE_PATH_CUSTOMERS + "/2/cart?quantity=5&productId=2").then().statusCode(200);
        Customer customer = when().get(BASE_PATH_CUSTOMERS + "/2").then().extract().as(Customer.class);
        Assert.assertEquals(customer.getShoppingCart().getItems().length, 3);
    }

    @Test
    public void putItemToCartAndCheckIt() {
        String customerId = "3";
        emptyCartForCustomer(customerId);
        putItemToCart(customerId, 2, 5);
        Customer customer = when().get(BASE_PATH_CUSTOMERS + SEPARATOR + customerId).as(Customer.class);
        Assert.assertEquals(customer.getShoppingCart().getItems().length, 1);
    }

    private void emptyCartForCustomer(String customerId) {
        when().delete(BASE_PATH_CUSTOMERS + SEPARATOR + customerId + SEPARATOR + "cart/empty").then().statusCode(200);
    }


}
