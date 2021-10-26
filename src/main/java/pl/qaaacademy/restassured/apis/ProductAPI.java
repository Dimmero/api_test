package pl.qaaacademy.restassured.apis;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.qaaacademy.restassured.environments.Environment;
import pl.qaaacademy.restassured.models.Product;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ProductAPI {
    private final String HOST;
    private final String SEPARATOR = "/";
    private final String PRODUCTS_ENDPOINT = "/products";
    private final Environment env;
    private RequestSpecification requestSpecification;

    public ProductAPI(Environment env) {
        this.env = env;
        HOST = env.getHost();
    }

    public static ProductAPI get(Environment env) {
        return new ProductAPI(env);
    }

    public HashMap<String, Object> createNewProduct(Product product) {
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("description", product.getDescription());
        productData.put("manufacturer", product.getManufacturer());
        productData.put("price", product.getPrice());
        return productData;
    }

    public Product addNewProductToItemListAndExtractId(Product product) {
        return given().body(createNewProduct(product)).contentType(ContentType.JSON).log().body()
                .when().post(HOST + PRODUCTS_ENDPOINT)
                .then().extract().as(Product.class);
    }

    public Product getProductBodyById(String productId) {
        return when().get(HOST + PRODUCTS_ENDPOINT + SEPARATOR + productId).then().extract().as(Product.class);
    }

    public Product updatePriceForProduct(float price, String productId) {
        Product product = getProductBodyById(productId);
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("description", product.getDescription());
        productData.put("id", productId);
        productData.put("manufacturer", product.getManufacturer());
        productData.put("price", price);
        return given().contentType(ContentType.JSON).body(productData).log().body()
                .when().put(HOST + PRODUCTS_ENDPOINT + SEPARATOR + productId)
                .then().extract().as(Product.class);
    }
}
