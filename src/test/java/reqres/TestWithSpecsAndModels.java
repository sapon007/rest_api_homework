package reqres;

import org.junit.jupiter.api.Test;
import reqres.models.*;
import reqres.models.lombokmodelsforlistusers.ListUsersModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reqres.specs.RestApiSpecs.*;

public class TestWithSpecsAndModels extends TestBase {


    @Test
    void successfulRegisterWithPojoAndAllureTest() {
        RegisterBodyPojoModel registerBody = new RegisterBodyPojoModel();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");

        RegisterResponsePojoModel response = step("Make request", () ->
                given(requestSpec)
                        .body(registerBody)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(RegisterResponsePojoModel.class));

        step("Verify response", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
            assertEquals(4, response.getId());
        });

    }

    @Test
    void successfulRegisterWithLombokTest() {
        RegisterBodyLombokModel registerBody = new RegisterBodyLombokModel();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");

        RegisterResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                        .body(registerBody)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(RegisterResponseLombokModel.class));

        step("Verify response", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
            assertEquals(4, response.getId());
        });
    }

    @Test
    void successfulGettingSingleUserTest() {
        step("Getting single user", () -> {
            given(requestSpec)
                    .when()
                    .get("/users/2")
                    .then()
                    .spec(responseSpec)
                    .statusCode(200);
        });

    }

    @Test
    void check404WhenSingleUserNotFountTest() {
        step("Check 404 when single user not found", () -> {
            given(requestSpec)
                    .when()
                    .get("/users/23")
                    .then()
                    .spec(responseSpec)
                    .statusCode(404);
        });

    }

    @Test
    void successfulGettingListUsersTest() {
        step("Getting list of users", () -> {
            given(requestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(responseSpec)
                    .statusCode(200)
                    .body("page", is(2))
                    .body("total",is(12));
        });

    }

    @Test
    void successfulGettingListUsersWithLombokTest() {
        ListUsersModel response = step("Getting list of users with lombok", () ->
                given(requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .body("page", is(2))
                        .body("total",is(12))
                        .extract().as(ListUsersModel.class));

        step("Verify response", () -> {
            assertEquals(2, response.getPage());
            assertEquals(12, response.getTotal());
        });
    }

    @Test
    void deletingUserTest() {
        step("Deleting user", () -> {
            given(requestSpec)
                    .when()
                    .delete("/users/2")
                    .then()
                    .spec(responseSpec)
                    .statusCode(204);
        });
    }
}
