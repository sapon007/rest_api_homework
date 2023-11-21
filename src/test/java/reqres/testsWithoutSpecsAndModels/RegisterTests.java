package reqres.testsWithoutSpecsAndModels;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import reqres.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegisterTests extends TestBase {

    String registerBody = "{ \"email\": \"eve.holt@reqres.in\",    \"password\": \"pistol\"}";

    @Test
    void successfulRegisterTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(registerBody)
                .contentType(ContentType.JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
