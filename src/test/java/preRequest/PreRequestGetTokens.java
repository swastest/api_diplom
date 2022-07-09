package preRequest;

import config.AdminPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;

public class PreRequestGetTokens {

    static public String getTokenAdmin() {
        AdminPropInterface config = ConfigFactory.create(AdminPropInterface.class);
        Response r = given()
                .spec(request)
                .queryParam("password", config.passwordAdmin())
                .queryParam("email", config.emailAdmin())
                .post("/auth")
                .then().log().all()
                .extract().response();
        String a = r.getHeader("Auth-Token");
        System.out.println("ТОКЕН АДМИН === " + a);
        return a;
    }

    static public String getTokenManager() {
        ManagerPropInterface config = ConfigFactory.create(ManagerPropInterface.class);
        Response r = given()
                .spec(request)
                .queryParam("password", config.passwordManager())
                .queryParam("email", config.emailManager())
                .post("/auth")
                .then()
                .extract().response();
        String a = r.getHeader("Auth-Token");
        System.out.println("ТОКЕН МЕНЕДЖЕР === " + a);
        return a;
    }


    static public String getTokenTech() {
        TechPropInterface config = ConfigFactory.create(TechPropInterface.class);
        Response r = given()
                .spec(request)
                .queryParam("password", config.passwordTech())
                .queryParam("email", config.emailTech())
                .post("/auth")
                .then()
                .extract().response();
        String a = r.getHeader("Auth-Token");
        System.out.println("ТОКЕН ТЕХНИК === " + a);
        return a;
    }


}
