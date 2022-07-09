package tests;

import com.github.javafaker.Faker;
import config.AdminPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import models.CreateOrUpdateNoteDto;
import models.RequestPaymentsItem;
import models.respAuthModels.ResponseAuthLombok;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import preRequest.PreRequestGetTokens;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class AuthTests {
    static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class);
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);
    static ManagerPropInterface confMng = ConfigFactory.create(ManagerPropInterface.class);

        static Stream<Arguments> LoginPasswordParamsAdminManagerTech(){
            return Stream.of
                    (Arguments.of(configAdm.passwordAdmin(), configAdm.emailAdmin(), "Админ"),
                            Arguments.of(confMng.passwordManager(),confMng.emailManager(), "Менеджер"),
                            Arguments.of(configTech.passwordTech(), configTech.emailTech(), "Техник")
                    );
        }

    @ParameterizedTest( name = "{2} авторизируется по логину и паролю")
    @MethodSource(value = "tests.Params#LoginPasswordParamsAdminManagerTech")
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

    @Test
    void simpleTest() {
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0,
                0, testTxt, configTech.idTechUser());
        given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .body("status", is("SUCCESS"))
                .body("data", notNullValue())
                .body("data.id", notNullValue())
                .body("data.user.id", is(configTech.idTechUser()))
                .body("data.owner.id", is(configAdm.idAdminUser()))
                .body("data.text", is(testTxt));

    }

    @Test
    void paymentAdminToTech() {
        RequestPaymentsItem item = new RequestPaymentsItem();
        item.setDate(1653657301000l);
        item.setDistance(0);
        item.setName("");
        item.setTeamId(0);
        item.setUserId(129);
        item.setTime(1653657301000l);

        given()
                .spec(request)
                .body(new RequestPaymentsItem[]{item})
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .when()
                .post("/payments")
                .then()
                .spec(response200)
                .extract();
        //.as(ResponsePayment.class);
        int a = 0;
    }

}
