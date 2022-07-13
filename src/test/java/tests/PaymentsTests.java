package tests;

import config.ConfigCenter;
import io.qameta.allure.AllureId;
import models.RequestPaymentsItem;
import models.addTypePayment.AddTypeCashModel;
import models.addTypePayment.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import preRequests.PreRequestGetTokens;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static tests.specification.Specs.request;
import static tests.specification.Specs.response200;

@Tag("payments")
public class PaymentsTests {
    @ParameterizedTest(name = "{1} добавляет оплату с указанием типа оплаты")
    @AllureId("11043")
    @MethodSource(value = "helpers.Params#preRequestParamTokenAdminManagerTech")
    void addsPayment(String value, String forNameTest) {
        List<ItemsItem> list = new ArrayList<>();
        ItemsItem paymentItem1 = new ItemsItem();
        AddTypeCashModel body = new AddTypeCashModel();
        ItemsItem paymentItem2 = new ItemsItem();

        paymentItem1.setAmount(10);
        paymentItem1.setType("cash");

        paymentItem2.setAmount(15);
        paymentItem2.setType("credit");

        list.add(paymentItem1);
        list.add(paymentItem2);

        body.setItems(list);
        given()
                .spec(request)
                .header("Authorization", value)
                .pathParam("id", "2001")
                .body(body)
                .when().log().all()
                .put("/rest/tasks/{id}/payment")
                .then()
                .spec(response200)
                .body("status", is("SUCCESS"));
    }

    @Test
    @AllureId("11044")
    @DisplayName("Оплата проставлена Админом технику")
    void addPaymentAdminToTech() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        String paymentComment = "Хорошая оплата такая";
        Long epoch = System.currentTimeMillis();

        RequestPaymentsItem item = new RequestPaymentsItem();
        item.setDate(epoch);
        item.setDistance(0);
        item.setName(paymentComment);
        item.setTeamId(0);
        item.setUserId(ConfigCenter.configTech.idTechUser());
        item.setTime(epoch);

        given()
                .spec(request)
                .body(new RequestPaymentsItem[]{item})
                .header("Authorization", token.getTokenAdmin())
                .when()
                .post("/payments")
                .then()
                .spec(response200)
                .body("status", is("SUCCESS"))
                .body("data", notNullValue())
                .body("data.items", notNullValue());
    }
}