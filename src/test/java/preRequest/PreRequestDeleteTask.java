package preRequest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PreRequestDeleteTask {

    static public void deleteTask(Integer value) {
        given()
                .spec(request)
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .pathParam("id", value)
                .contentType(JSON)
                .when()
                .delete("/tasks/{id}")
                .then()
                .spec(response200);
    }

}
