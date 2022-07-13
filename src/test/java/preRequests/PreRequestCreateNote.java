package preRequests;

import com.github.javafaker.Faker;
import config.ConfigCenter;
import models.CreateOrUpdateNoteDto;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PreRequestCreateNote {
    public Integer getIdNewNoteTeamFromAdmin() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        Faker faker = new Faker();
        Long epoch = System.currentTimeMillis();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, ConfigCenter.configTeam.teamId(), 0, testTxt, 0);
        return given()
                .spec(request)
                .header("Authorization", token.getTokenAdmin())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }

    public Integer getIdNewNoteTeamFromManager() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, ConfigCenter.configTeam.teamId(), 0, testTxt, 0);
        return given()
                .spec(request)
                .header("Authorization", token.getTokenManager())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }

    public Integer getIdNewNoteTechToSelf() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();
        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0, 0, testTxt, ConfigCenter.configTech.idTechUser());
        return given()
                .spec(request)
                .header("Authorization", token.getTokenTech())
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().jsonPath().get("data.id");
    }
}
