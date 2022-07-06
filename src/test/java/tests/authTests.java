package tests;

import com.github.javafaker.Faker;
import config.AdminPropInterface;
import config.LinkPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import io.restassured.http.ContentType;
import models.CreateOrUpdateNoteDto;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import tests.preRequest.PreRequestToken;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class authTests {
    static LinkPropInterface linkConfig = ConfigFactory.create(LinkPropInterface.class);
    static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class);
    static ManagerPropInterface configMng = ConfigFactory.create(ManagerPropInterface.class);
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);

    @Test
    void authAdmin(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("password", configAdm.passwordAdmin())
                .queryParam("email", configAdm.emailAdmin())
                .when().log().all()
                .post(linkConfig.baseUrl()+"/rest/auth")
                .then().log().all()
                .extract();
        int a =0;

    }

    @Test
    void simpleTest(){
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0,
                0, testTxt, configTech.idTechUser());
        given()
                .header("Authorization", PreRequestToken.getTokenAdmin())
                .body(body)
                .contentType(ContentType.JSON)
                .when().log().body()
                .put(linkConfig.baseUrl()+"/rest/notes")
                .then().log().all()
                .statusCode(200)
                .body("status", is("SUCCESS"))
                .body("data", notNullValue())
                .body("data.id", notNullValue())
                .body("data.user.id", is(configTech.idTechUser()))
                .body("data.owner.id", is(configAdm.idAdminUser()))
                .body("data.text", is(testTxt));

    }

}
