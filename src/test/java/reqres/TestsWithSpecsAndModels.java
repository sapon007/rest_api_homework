package reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static reqres.specs.RestApiSpecs.*;

public class TestsWithSpecsAndModels extends TestBase {
    String registerBody = "{ \"email\": \"eve.holt@reqres.in\",    \"password\": \"pistol\"}";

    @Test
    void successfulRegisterTest() {
        given(requestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulGettingSingleUserTest() {
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec);
    }

    @Test
    void check404WhenSingleUserNotFountTest() {
        given(requestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(response404Spec)
                .statusCode(404);
    }

    @Test
    void successfulGettingListUsersTest() {
        given(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .body("page", is(2))
                .body("total",is(12));
    }

    @Test
    void deletingUserTest() {
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(response204Spec);
    }
}
