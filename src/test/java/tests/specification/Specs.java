package tests.specification;

import config.AdminPropInterface;
import config.LinkPropInterface;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {
    static LinkPropInterface linkConfig = ConfigFactory.create(LinkPropInterface.class);

    public static RequestSpecification request;

    static {
        request = with()
                .filter(withCustomTemplates())
                .baseUri(linkConfig.baseUrl())
                .basePath("/rest")
                .log().all()
                .contentType(ContentType.JSON);
    }



    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification response403 = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(ALL)
            .build();

    public static ResponseSpecification response405 = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .log(ALL)
            .build();
}
