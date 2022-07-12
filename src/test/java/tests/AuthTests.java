package tests;

import io.qameta.allure.AllureId;
import models.respAuthModels.ResponseAuthLombok;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

@Tag("auth")
public class AuthTests {
    @ParameterizedTest(name = "{2} авторизуется по логину и паролю")
    @AllureId("11041")
    @MethodSource(value = "helpers.Params#loginPasswordParamsAdminManagerTech")
    void authTests(String password, String email, String forNameTest) {
        ResponseAuthLombok resp = given()
                .spec(request)
                .queryParam("password", password)
                .queryParam("email", email)
                .when()
                .post("/auth")
                .then()
                .spec(response200)
                .extract().as(ResponseAuthLombok.class);
        Assertions.assertNotNull(resp.getData().getPhones().get(0).getToken());
        Assertions.assertEquals(email, resp.getData().getEmail());
    }
}
