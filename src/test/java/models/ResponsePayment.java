package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePayment {

    private Data data;
    private String message;
    private String status;

public class Items {

    private Integer date;
    private Integer amount;
    private String paymentMethod;
    private Integer jobNumber;
    private String collector;

}

public class Data {

    private List<Items> items;

}

}
