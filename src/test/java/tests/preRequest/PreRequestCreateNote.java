package tests.preRequest;

import com.github.javafaker.Faker;
import config.LinkPropInterface;
import config.TeamPropInterface;
import config.TechPropInterface;
import io.restassured.http.ContentType;
import models.CreateOrUpdateNoteDto;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public  class PreRequestCreateNote {

    static public Integer getIdNewNoteTeamFromAdmin() {
        LinkPropInterface linkConfig = ConfigFactory.create(LinkPropInterface.class);
        TeamPropInterface teamConf = ConfigFactory.create(TeamPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, teamConf.teamId(), 0, testTxt, 0);
       Integer a = given()
                .header("Authorization", PreRequestToken.getTokenAdmin())
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .put(linkConfig.baseUrl() + "/rest/notes")
                .then().log().all()
                .extract().jsonPath().get("data.id");
        return a;
    }
    static public Integer getIdNewNoteTeamFromManager() {
        LinkPropInterface linkConfig = ConfigFactory.create(LinkPropInterface.class);
        TeamPropInterface teamConf = ConfigFactory.create(TeamPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, teamConf.teamId(), 0, testTxt, 0);
        Integer a = given()
                .header("Authorization", PreRequestToken.getTokenManager())
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .put(linkConfig.baseUrl() + "/rest/notes")
                .then().log().all()
                .extract().jsonPath().get("data.id");
        return a;
    }

    static public Integer getIdNewNoteTechToSelf() {
        LinkPropInterface linkConfig = ConfigFactory.create(LinkPropInterface.class);
        TechPropInterface techConfig = ConfigFactory.create(TechPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0, 0, testTxt, techConfig.idTechUser());
       Integer a = given()
                .header("Authorization", PreRequestToken.getTokenTech())
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .put(linkConfig.baseUrl() + "/rest/notes")
                .then().log().all()
                .extract().jsonPath().get("data.id");
        return a;
    }

}
