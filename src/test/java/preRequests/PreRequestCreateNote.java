package preRequests;

import com.github.javafaker.Faker;
import config.TeamPropInterface;
import config.TechPropInterface;
import models.CreateOrUpdateNoteDto;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PreRequestCreateNote {

    static public Integer getIdNewNoteTeamFromAdmin() {
        TeamPropInterface teamConf = ConfigFactory.create(TeamPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, teamConf.teamId(), 0, testTxt, 0);
        Integer a = given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
        return a;
    }

    static public Integer getIdNewNoteTeamFromManager() {
        TeamPropInterface teamConf = ConfigFactory.create(TeamPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, teamConf.teamId(), 0, testTxt, 0);
        Integer a = given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenManager())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
        return a;
    }

    static public Integer getIdNewNoteTechToSelf() {
        TechPropInterface techConfig = ConfigFactory.create(TechPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0, 0, testTxt, techConfig.idTechUser());
        Integer a = given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenTech())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
        return a;
    }

}
