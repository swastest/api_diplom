package models.respAddPayment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddPayment {
	private List<ResponseAddPaymentItem> responseAddNote;
}