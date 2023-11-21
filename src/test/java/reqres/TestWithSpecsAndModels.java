package reqres;

import org.junit.jupiter.api.Test;
import reqres.models.*;
import reqres.models.lombokmodelsforlistusers.ListUsersModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
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
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
            assertThat(response.getId()).isEqualTo(4);
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
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
            assertThat(response.getId()).isEqualTo(4);
        });
    }

    @Test
    void successfulGettingSingleUserTest() {
        SingleUserModel response = step("Getting single user", () ->
                        given(requestSpec)
                                .when()
                                .get("/users/2")
                                .then()
                                .spec(responseSpec)
                                .statusCode(200)
                                .extract().as(SingleUserModel.class)
                   );

        step("Verify response data{}", () -> {
            assertThat(response.getData().getId()).isEqualTo(2);
            assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
            assertThat(response.getData().getFirstName()).isEqualTo("Janet");
            assertThat(response.getData().getLastName()).isEqualTo("Weaver");
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
    void successfulGettingListUsersWithLombokTest() {
        ListUsersModel response = step("Getting list of users with lombok", () ->
                given(requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(ListUsersModel.class));

        step("Verify response", () -> {
            assertThat(response.getPage()).isEqualTo(2);
            assertThat(response.getTotal()).isEqualTo(12);
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
