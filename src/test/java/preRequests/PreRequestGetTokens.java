package preRequests;

import config.AdminPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;

public class PreRequestGetTokens {

    static public String getTokenAdmin() {
        AdminPropInterface config = ConfigFactory.create(AdminPropInterface.class);
        return given()
                .spec(request)
                .queryParam("password", config.passwordAdmin())
                .queryParam("email", config.emailAdmin())
                .post("/auth")
                .then().log().all()
                .extract().response().getHeader("Auth-Token");
    }

    static public String getTokenManager() {
        ManagerPropInterface config = ConfigFactory.create(ManagerPropInterface.class);
        return given()
                .spec(request)
                .queryParam("password", config.passwordManager())
                .queryParam("email", config.emailManager())
                .post("/auth")
                .then()
                .extract().response().getHeader("Auth-Token");
    }


    static public String getTokenTech() {
        TechPropInterface config = ConfigFactory.create(TechPropInterface.class);
        return given()
                .spec(request)
                .queryParam("password", config.passwordTech())
                .queryParam("email", config.emailTech())
                .post("/auth")
                .then()
                .extract().response().getHeader("Auth-Token");
    }


}
