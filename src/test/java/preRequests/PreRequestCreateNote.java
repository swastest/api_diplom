package preRequests;

import com.github.javafaker.Faker;
import config.ConfigCenter;
import models.CreateOrUpdateNoteDto;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PreRequestCreateNote {
    static public Integer getIdNewNoteTeamFromAdmin() {
        Faker faker = new Faker();
        Long epoch = System.currentTimeMillis();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, ConfigCenter.configTeam.teamId(), 0, testTxt, 0);
        return given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }

    static public Integer getIdNewNoteTeamFromManager() {
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, ConfigCenter.configTeam.teamId(), 0, testTxt, 0);
        return given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenManager())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }

    static public Integer getIdNewNoteTechToSelf() {
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0, 0, testTxt, ConfigCenter.configTech.idTechUser());
        return given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenTech())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }
}
