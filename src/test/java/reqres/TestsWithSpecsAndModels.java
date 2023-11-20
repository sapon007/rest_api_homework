package reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static reqres.specs.RegisterSpec.*;

public class TestsWithSpecsAndModels extends TestBase {
    String registerBody = "{ \"email\": \"eve.holt@reqres.in\",    \"password\": \"pistol\"}";

    @Test
    void successfulRegisterTest() {
        given(registerRequestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(registerResponseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
