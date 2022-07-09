package tests;

import config.TechPropInterface;
import models.RequestPaymentsItem;
import models.respAddPayment.ResponseAddPaymentItem;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import preRequests.PreRequestGetTokens;

import static io.restassured.RestAssured.given;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

public class PaymentsTests {
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);

    @Test
    void addPaymentAdminToTech() {
        String paymentComment = "Хорошая оплата такая";
        Long epoch = System.currentTimeMillis();

        RequestPaymentsItem item = new RequestPaymentsItem();
        item.setDate(epoch);
        item.setDistance(0);
        item.setName(paymentComment);
        item.setTeamId(0);
        item.setUserId(configTech.idTechUser());
        item.setTime(epoch);

        ResponseAddPaymentItem[] resp = given()
                .spec(request)
                .body(new RequestPaymentsItem[]{item})
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .when()
                .post("/payments")
                .then()
                .spec(response200)
                .extract().as(ResponseAddPaymentItem[].class);
        /*
        Body:
[
    {
        "date": 1657398849962,
        "distance": 0,
        "teamId": 0,
        "name": "Хорошая оплата такая",
        "time": 1657398849962,
        "userId": 57
    }
]

как принять?
как преиспользовать модели?
         */
    }
}
