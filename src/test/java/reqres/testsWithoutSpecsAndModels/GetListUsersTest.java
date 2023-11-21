package reqres.testsWithoutSpecsAndModels;

import org.junit.jupiter.api.Test;
import reqres.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetListUsersTest extends TestBase {
    @Test
    void successfulGettingListUsersTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("total",is(12));
    }
}
