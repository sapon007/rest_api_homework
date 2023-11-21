package reqres.testsWithoutSpecsAndModels;


import org.junit.jupiter.api.Test;
import reqres.TestBase;

import static io.restassured.RestAssured.given;

public class GetSingleUserTests extends TestBase {
    @Test
    void successfulGettingSingleUserTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void check404WhenSingleUserNotFountTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}
