package tests.preRequest;

import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PreRequestTasks extends TestBase {
/*
    static public Integer getIdTask() {
        Long epoch = System.currentTimeMillis();
        Long epochPlus = System.currentTimeMillis() + 3600000;

        RestTasks body = new RestTasks(epochPlus, 14743, epoch, 0, epochPlus,
                "Test text", 2, epoch, 128);
        Integer response = given()
                .header("Authorization", PreRequestToken.getTokenAdmin())
                .body(body)
                .queryParam("on_hold", false)
                .contentType(JSON)
                .when()
                .put("/rest/tasks")
                .then()
                .extract().path("data.id");
        System.out.println("ЗАДАЧА НОМЕР === " +response);
        return response;
    }
*/
    static public void deleteTask(Integer value) {
        given()
                .header("Authorization", PreRequestToken.getTokenAdmin())
                .pathParam("id", value)
                .contentType(JSON)
                .when()
                .delete("/rest/tasks/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void tst(){
        deleteTask(2552);
    }
}
