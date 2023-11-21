package reqres;

import org.junit.jupiter.api.Test;
import reqres.models.*;
import reqres.models.lombokModelsForListUsers.ListUsersModel;

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

        RegisterResponseLombokModel response = given(requestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .extract().as(RegisterResponseLombokModel.class);
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
    void successfulGettingListUsersWithLombokTest() {
        ListUsersModel response = given(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .body("page", is(2))
                .body("total",is(12))
                .extract().as(ListUsersModel.class);
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
