package tests;

import com.github.javafaker.Faker;
import config.TechPropInterface;
import models.CreateOrUpdateNoteDto;
import models.respAddNoteToTech.ResponseAddNote;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

@Tag("note")
public class NotesTests {
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);

    @ParameterizedTest(name = "{2} создает заметку для техника")
    @MethodSource(value = "helpers.Params#preRequestParamTokenAdminManagerAndUserId")
    void addNoteForTech(String token, Integer userId, String forNameTest) {
        Long epoch = System.currentTimeMillis();
        Faker faker = new Faker();
        String testTxt = faker.backToTheFuture().quote();

        CreateOrUpdateNoteDto body = new CreateOrUpdateNoteDto(epoch, 0,
                0, testTxt, configTech.idTechUser());
        ResponseAddNote resp = given()
                .spec(request)
                .header("Authorization", token)
                .body(body)
                .when()
                .put("/notes")
                .then()
                .spec(response200)
                .extract().as(ResponseAddNote.class);
        Assertions.assertNotNull(resp.getData().getId());
        Assertions.assertEquals(resp.getData().getUser().getId(), configTech.idTechUser());
        Assertions.assertEquals(resp.getStatus(), "SUCCESS");
        Assertions.assertEquals(resp.getData().getText(), testTxt);
        Assertions.assertEquals(resp.getData().getOwner().getId(), userId);
    }
}
