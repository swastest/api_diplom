package tests;

import config.TechPropInterface;
import models.RequestPaymentsItem;
import models.addTypePayment.AddTypeCashModel;
import models.addTypePayment.ItemsItem;
import org.aeonbits.owner.ConfigFactory;
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
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);

    @ParameterizedTest(name = "{1} добавляет оплату с указанием типа оплаты")
    @MethodSource(value = "helpers.Params#preRequestParamTokenAdminManagerTech")
    void addsPayment(String value, String forNameTest) {
        List<ItemsItem> list = new ArrayList<>();
        ItemsItem pyItem1 = new ItemsItem();
        pyItem1.setAmount(10);
        pyItem1.setType("cash");
        ItemsItem pyItem2 = new ItemsItem();
        pyItem2.setAmount(15);
        pyItem2.setType("credit");
        list.add(pyItem1);
        list.add(pyItem2);

        AddTypeCashModel body = new AddTypeCashModel();
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
    @DisplayName("Оплата проставлена Админом технику")
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

        given()
                .spec(request)
                .body(new RequestPaymentsItem[]{item})
                .header("Authorization", PreRequestGetTokens.getTokenAdmin())
                .when()
                .post("/payments")
                .then()
                .spec(response200)
                .body("status", is("SUCCESS"))
                .body("data", notNullValue())
                .body("data.items", notNullValue());

    }
}