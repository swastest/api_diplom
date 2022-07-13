package preRequests;

import config.ConfigCenter;
import models.RequestAddTask;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PreRequestTask {
    public Integer getIdTask() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        Long epoch = System.currentTimeMillis();
        Long epochPlus = System.currentTimeMillis() + 3600000;
        RequestAddTask body = new RequestAddTask();
        body.setJobEndDate(epochPlus);
        body.setJobStartDate(epoch);
        body.setCustomerAvailableStartDate(epoch);
        body.setCustomerAvailableEndDate(epochPlus);
        body.setClientId(ConfigCenter.configClient.idClient());
        body.setTeamId(0);
        body.setUserId(ConfigCenter.configTech.idTechUser());
        body.setId(0);
        body.setDescription("Новая задача");
        return given()
                .spec(request)
                .header("Authorization", token.getTokenAdmin())
                .body(body)
                .queryParam("on_hold", false)
                .contentType(JSON)
                .when()
                .put("/tasks")
                .then()
                .extract().path("data.id");
    }

    public void deleteTask (Integer value) {
        PreRequestGetTokens token = new PreRequestGetTokens();
        given()
                .spec(request)
                .header("Authorization", token.getTokenAdmin())
                .pathParam("id", value)
                .contentType(JSON)
                .when()
                .delete("/tasks/{id}")
                .then()
                .spec(response200);
    }
}
