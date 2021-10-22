package pl.qaaacademy.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BasicCustomerVerificationTest {

    @Test
    public void should200WhenFetchingCustomerList() {
        when().get("http://localhost:3000/customers")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test
    public void shouldReturnCustomerInfoForWithIDEquals2() {
        Response response = when().get("http://localhost:3000/customers/2")
                .then()
                .extract()
                .response();
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.statusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeaders().asList());
        System.out.println(response.getCookies());
//                .statusCode(200)
//                .body("person.email", is(equalTo("john.doe@customDomain.com")))
//                .body("address.phone", is(equalTo("33 55 789 123")));
    }
}
