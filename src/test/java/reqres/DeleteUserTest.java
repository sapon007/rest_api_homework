package reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends TestBase {
    @Test
    void deletingUserTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
