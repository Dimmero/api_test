package pl.qaaacademy.restassured.apis;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.qaaacademy.restassured.environments.Environment;
import pl.qaaacademy.restassured.models.Customer;
import pl.qaaacademy.restassured.models.Product;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CustomerAPI {

    private static final String SEPARATOR = "/";
    private final String HOST;
    private final Environment env;
    private RequestSpecification reqSpec;
    private final String CHANGE_EMAIL_ENDPOINT = "/email";
    private final String CUSTOMERS = "/customers";
    private final String PRODUCTS_ENDPOINT = "/products";


    private CustomerAPI(Environment env) {
        this.env = env;
        HOST = env.getHost();
        requestSetUp();
    }

    public static CustomerAPI get(Environment env) {
        return new CustomerAPI(env);
    }

    public List<Customer> getAllCustomers() {
        String query = HOST + CUSTOMERS;
        return when().get(query).then()
                .log().body()
                .extract().body().jsonPath().getList("", Customer.class);
    }

    private void requestSetUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setAccept(ContentType.JSON);
        builder.addCookie("cookie", "key1=value1");
        builder.addHeader("UsefulHeader", "veryUsefulValue");
        reqSpec = builder.build();
    }

    public Customer updateEmailAddressForCustomer(String customerId, String newEmail) {
        String address = HOST + CUSTOMERS + SEPARATOR + customerId + CHANGE_EMAIL_ENDPOINT;
        Customer alex = given().queryParam("email", newEmail).when().patch(address).then().log().all().extract().body().as(Customer.class);
        return alex;
    }

    public void addItemToCartForCustomer(String customerId, String itemId, String quantity) {
        String endPointForAddingItem = HOST + CUSTOMERS + SEPARATOR + customerId + SEPARATOR + "cart";
        given().queryParam("quantity", quantity).and().queryParam("productId", itemId).put(endPointForAddingItem).then().log().all();
    }

    public Customer getCustomerById(String customerId) {
        return when().get(HOST + CUSTOMERS + SEPARATOR + customerId).then().log().body().extract().as(Customer.class);
    }


}
