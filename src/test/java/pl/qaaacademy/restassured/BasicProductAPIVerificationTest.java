package pl.qaaacademy.restassured;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BasicProductAPIVerificationTest {

    private final String BASE_PATH_PRODUCTS = "http://localhost:3000/products";
    private final String BASE_PATH_CUSTOMERS = "http://localhost:3000/customers";
    private final String SEPARATOR = "/";


    @Test
    public void shouldAddNewProduct() {
        given().body("{\n" +
                        "    \"description\": \"Yerba Mate\",\n" +
                        "    \"manufacturer\": 8,\n" +
                        "    \"price\": 20\n" +
                        "}").log().all()
                .when().post(BASE_PATH_PRODUCTS)
                .then().log().all().statusLine(containsString("OK"));
    }

    @Test
    public void shouldUpdatePriceForProductWithID2() {
        Double newPrice = 15.3;
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("description", "Banana");
        productData.put("id", "2");
        productData.put("manufacturer", "1");
        productData.put("price", newPrice);
        given().contentType(ContentType.JSON)
                .body(productData).log().body()
                .when().put(BASE_PATH_PRODUCTS + "/2")
                .then().log().all().statusLine(containsString("OK"));
    }

    @Test
    public void testIfStrawberryProductIsInList() {
        String product1 = "Strawberry";
        String product2 = "Peach";
        ArrayList<String> listOfProducts =
                when().get(BASE_PATH_PRODUCTS)
                        .then().contentType(ContentType.JSON)
                        .extract()
                        .path("description");
        assertThat(listOfProducts, hasItems(product1, product2));
    }

    @Test
    public void testIfPeachProductIsInList() {
        double strawberryPrice = 18.3;
        when().get(BASE_PATH_PRODUCTS + "/6")
                .then()
                .statusCode(200)
                .body("price", is((float) strawberryPrice));
    }

    @Test
    public void shouldDeletePreviouslyCreatedItem() {
        String id = given().body("""
                        {
                            "description": "Roasted coffee",
                            "manufacturer": 2,
                            "price": 22.1
                        }""")
                .contentType(ContentType.JSON)
                .log()
                .body()
                .when()
                .post(BASE_PATH_PRODUCTS)
                .then()
                .extract()
                .path("id");
        given()
                .delete(BASE_PATH_PRODUCTS + "/" + id)
                .then()
                .log()
                .body();

    }

    @Test
    public void shouldVerifyAddressCityForCustomerWithId3() {
        Header h1 = new Header("h1", "v1");
        String expectedCity = "Auckland";
        String base = "find {it.id == '3'}.address.city";
        given().header(h1).log().headers().
        when().get(BASE_PATH_CUSTOMERS).then().body(base, equalTo(expectedCity));
    }

    @Test
    public void extractedProductShouldHaveExpectedDescription() {
        String productID = "3";
        String expectedDescription = "Grapes";
        Product grapes = given().
                when().get(BASE_PATH_PRODUCTS + SEPARATOR + productID)
                .then().extract().body().as(Product.class);
        Assert.assertEquals(expectedDescription, grapes.getDescription());
    }

    @Test
    public void extractedProductShouldHaveExpectedDescription1() {
        String productID = "7";
        String expectedDescription = "Orange";
        float expectedPrice = 10.5f;
        Product orange = given().
                when().get(BASE_PATH_PRODUCTS + SEPARATOR + productID)
                .then().extract().body().as(Product.class);
        Assert.assertEquals(expectedDescription, orange.getDescription());
        Assert.assertEquals(expectedPrice, orange.getPrice());
    }
}
