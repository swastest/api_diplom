package tests;

import com.github.javafaker.Faker;
import config.AdminPropInterface;
import config.TechPropInterface;
import models.CreateOrUpdateNoteDto;
import models.RequestPaymentsItem;
import models.respAuthModels.ResponseAuthLombok;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import preRequest.PreRequestGetTokens;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class AuthTests {
    static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class);
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);

    @Test
    void authAdmin() {
        ResponseAuthLombok resp = given()
                .spec(request)
                .queryParam("password", configAdm.passwordAdmin())
                .queryParam("email", configAdm.emailAdmin())
                .when().log().all()
                .post("/auth")
                .then()
                .spec(response200)
                .extract().as(ResponseAuthLombok.class);
        Assertions.assertNotNull(resp.getData().getPhones().get(0).getToken());
        Assertions.assertEquals(configAdm.emailAdmin(), resp.getData().getEmail());
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
                .when().log().body()
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
