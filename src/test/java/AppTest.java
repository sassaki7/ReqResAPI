import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {
    @Test public void loginSucceeded(){
        given().
            contentType(ContentType.JSON).
            body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}").
        when().
            post("https://reqres.in/api/login").
        then().
            body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test public void loginFailed(){
        given().
                contentType(ContentType.JSON).
                body("{\"email\": \"eve.holt@reqres.in\"}").
                when().
                post("https://reqres.in/api/login").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", is("Missing password"));
    }
}
