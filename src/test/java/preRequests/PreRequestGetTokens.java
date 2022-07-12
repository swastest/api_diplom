package preRequests;

import config.ConfigCenter;
import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;

public class PreRequestGetTokens {

    static public String getTokenAdmin() {
        return given()
                .spec(request)
                .queryParam("password", ConfigCenter.configAdm.passwordAdmin())
                .queryParam("email", ConfigCenter.configAdm.emailAdmin())
                .post("/auth")
                .then().log().all()
                .extract().response().getHeader("Auth-Token");
    }

    static public String getTokenManager() {
        return given()
                .spec(request)
                .queryParam("password", ConfigCenter.configMng.passwordManager())
                .queryParam("email", ConfigCenter.configMng.emailManager())
                .post("/auth")
                .then()
                .extract().response().getHeader("Auth-Token");
    }

    static public String getTokenTech() {
        return given()
                .spec(request)
                .queryParam("password", ConfigCenter.configTech.passwordTech())
                .queryParam("email", ConfigCenter.configTech.emailTech())
                .post("/auth")
                .then()
                .extract().response().getHeader("Auth-Token");
    }
}
