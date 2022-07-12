package tests;

import config.ConfigCenter;
import io.qameta.allure.AllureId;
import models.RequestAddTask;
import models.respNewTask.ResponseAddNewTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import preRequests.PreRequestGetTokens;
import preRequests.PreRequestTask;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

@Tag("task")
public class TaskTests {
    @Test
    @AllureId("11045")
    @DisplayName("Админ добавляет новую задачу технику")
    void adminAddNewTask() {
        Long epoch = System.currentTimeMillis();
        Long epochPlus = System.currentTimeMillis() + 3600000;
        String taskName = "Админ назначил - значит надо!";

        RequestAddTask body = new RequestAddTask();
        body.setJobEndDate(epochPlus);
        body.setJobStartDate(epoch);
        body.setCustomerAvailableStartDate(epoch);
        body.setCustomerAvailableEndDate(epochPlus);
        body.setClientId(ConfigCenter.configClient.idClient());
        body.setTeamId(0);
        body.setUserId(ConfigCenter.configTech.idTechUser());
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
        Assertions.assertEquals(ConfigCenter.configClient.idClient(), resp.getData().getClient().getId());
        Assertions.assertEquals(taskName, resp.getData().getDescription());
    }

    @Test
    @AllureId("11046")
    @DisplayName("Админ меняет статус новой задачи на 'in_progress'")
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
