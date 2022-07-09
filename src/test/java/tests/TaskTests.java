package tests;

import config.ClientPropInterface;
import config.TechPropInterface;
import models.RequestAddTask;
import models.respNewTask.ResponseAddNewTask;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import preRequests.PreRequestGetTokens;
import preRequests.PreRequestTask;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class TaskTests {

    @Test
    @DisplayName("Админ добавляет новую задачу технику")
    void adminAddNewTask(){
        ClientPropInterface configClient = ConfigFactory.create(ClientPropInterface.class);
        TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);
        Long epoch = System.currentTimeMillis();
        Long epochPlus = System.currentTimeMillis() + 3600000;
        String taskName = "Админ назначил - значит надо!";

        RequestAddTask body = new RequestAddTask();
        body.setJobEndDate(epochPlus);
        body.setJobStartDate(epoch);
        body.setCustomerAvailableStartDate(epoch);
        body.setCustomerAvailableEndDate(epochPlus);
        body.setClientId(configClient.idClient());
        body.setTeamId(0);
        body.setUserId(128);
        body.setId(0);
        body.setDescription(taskName);
        ResponseAddNewTask resp = given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .body(body)
                .queryParam("on_hold", false)
                .contentType(JSON)
                .when()
                .put("/tasks")
                .then()
                .spec(response200)
                .extract().as(ResponseAddNewTask.class);
        PreRequestTask.deleteTask(resp.getData().getId());
        Assertions.assertEquals(configClient.idClient(), resp.getData().getClient().getId());
        Assertions.assertEquals(taskName, resp.getData().getDescription());
    }

    @DisplayName("Админ меняет статус новой задачи на 'in_progress'")
    @Test
    void AdminEditingTaskStatusInProgress() {
        Integer idTask = PreRequestTask.getIdTask();
        given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .queryParam("status", "in_progress")
                .pathParam("id", idTask)
                .when().log().all()
                .post("/tasks/{id}")
                .then().log().all()
                .spec(response200)
                .body("status", is("SUCCESS"));
        PreRequestTask.deleteTask(idTask);
    }
}
