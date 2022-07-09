package models.respAddPayment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddPaymentItem {
	private Long date;
	private Integer distance;
	private Integer teamId;
	private String name;
	private Long time;
	private Integer userId;
}